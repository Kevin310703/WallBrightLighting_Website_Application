package com.light.controller;

import com.light.dto.ListOrderDTO;
import com.light.dto.OrderProductDTO;
import com.light.dto.OrderStatusDTO;
import com.light.model.*;
import com.light.repositories.*;
import com.light.request.PlaceOrderRequest;
import com.light.request.ProductQuantityRequest;
import com.light.service.CommonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private CommonService commonService;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderStatusRepository orderStatusRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemsRepository cartItemsRepository;
    @Autowired
    private ProductStatusRepository productStatusRepository;

    @GetMapping("/history/{statusId}")
    public String history(@PathVariable Long statusId, Model model) {
        User currentUser = commonService.getCurrentUser();
        if (currentUser == null) {
            return "redirect:/auth/login";
        }
        long countOrder = orderRepository.findAll()
                .stream()
                .filter(o -> o.getUser().getId() == currentUser.getId() && o.getOrderstatus().getId() == statusId)
                .count();
        model.addAttribute("count", countOrder);

        List<ListOrderDTO> listOrder = orderRepository.findAll()
                .stream()
                .filter(o -> o.getUser().getId() == currentUser.getId() && o.getOrderstatus().getId() == statusId)
                .map(this::toListOrderDTO)
                .collect(Collectors.toList());
        model.addAttribute("history", listOrder);

        List<OrderStatus> orderStatus = orderStatusRepository.findAll();
        model.addAttribute("orderStatus", orderStatus);

        OrderStatus currentOrderStatus = orderStatusRepository.findById(statusId).orElse(null);
        if (currentOrderStatus != null) {
            model.addAttribute("orderStatusName", currentOrderStatus.getName());
        }

        model.addAttribute("statusId", statusId);
        model.addAttribute("user", currentUser);

        return "history";
    }

    @GetMapping("/all/{statusId}")
    public String all(@PathVariable Long statusId, Model model){
        User currentUser = commonService.getCurrentUser();
        if (currentUser == null && currentUser.getRole().getId() != 1) {
            return "redirect:/auth/login";
        }

        List<ListOrderDTO> listOrder = orderRepository.findAll()
                .stream()
                .filter(o -> o.getOrderstatus().getId() == statusId)
                .map(this::toListOrderDTO)
                .collect(Collectors.toList());
        model.addAttribute("orders", listOrder);

        List<OrderStatusDTO> orderStatus = orderStatusRepository.findAll()
                .stream()
                .map(os -> new OrderStatusDTO(os.getId(),os.getName()))
                .collect(Collectors.toList());
        model.addAttribute("orderStatus", orderStatus);
        return "allorder";
    }

    @PostMapping("/update/{orderId}")
    public String update(@PathVariable Long orderId, Long statusId){
        Order order = orderRepository.findById(orderId)
                .orElse(null);
        if(order != null){
            order.setOrderstatus(orderStatusRepository.findById(statusId).orElse(null));
            orderRepository.save(order);
        }
        return "redirect:/order/all/" + statusId;
    }

    @PostMapping("/add")
    public String add(PlaceOrderRequest request, Model model) {
        try {
            User user = commonService.getCurrentUser();
            Order newOrder = new Order();
            newOrder.setFirstname(request.getFirstName());
            newOrder.setLastName(request.getLastName());
            newOrder.setAddress(request.getAddress());
            newOrder.setPhone(request.getPhone());
            newOrder.setNotes(request.getNote());
            newOrder.setUser(user);
            newOrder.setOrderstatus(orderStatusRepository.findById(5L).orElse(null));
            newOrder.setOrderDate(new Timestamp(System.currentTimeMillis()));
            orderRepository.save(newOrder);

            for (ProductQuantityRequest p : request.getProductInfor()) {
                OrderDetail details = new OrderDetail();
                Product product = productRepository.findById(p.getProductID()).orElse(null);
                details.setOrder(newOrder);
                details.setProduct(product);
                details.setQuantity(p.getQuantity());
                details.setPrice(p.getPrice());
                orderDetailRepository.save(details);
            }

            BigDecimal getTotal = orderRepository.getTotal(newOrder.getId());
            Payment payment = paymentRepository.findById(Long.valueOf(request.getPayment())).orElse(null);

            Transaction newTransaction = new Transaction();
            newTransaction.setOrder(newOrder);
            newTransaction.setPayment(payment);
            newTransaction.setTotal(getTotal);
            transactionRepository.save(newTransaction);

            Cart userCart = cartRepository.findAll()
                    .stream()
                    .filter(c -> c.getUser().getId() == user.getId())
                    .findAny()
                    .orElse(null);

            for (ProductQuantityRequest p : request.getProductInfor()) {
                cartItemsRepository.findAll()
                        .stream()
                        .filter(ci -> {
                            assert userCart != null;
                            return ci.getCart().getId() == userCart.getId() && ci.getProduct().getId() == p.getProductID();
                        })
                        .findAny().ifPresent(cartItems -> cartItemsRepository.delete(cartItems));
            }
            for (ProductQuantityRequest p : request.getProductInfor()) {
                Product product = productRepository.findAll()
                        .stream()
                        .filter(pr -> pr.getId() == p.getProductID() && pr.getIsActive() == 1)
                        .findAny()
                        .orElse(null);
                if (product != null) {
                    product.setQuantity(product.getQuantity() - p.getQuantity());
                    if (product.getQuantity() == 0) {
                        product.setProductstatus(productStatusRepository.findById(4L).orElse(null));
                    }
                    productRepository.save(product);
                }
            }
            String message = "Order Successfully";
            return "redirect:/cart/index?orderSuccess=" + message;
        } catch (Exception ex) {
            return "redirect:/error/index";
        }
    }

    @PostMapping("/cancel")
    public String cancel(@RequestParam Long orderId) {
        try {
            Order order = orderRepository.findAll()
                    .stream()
                    .filter(o -> o.getId() == orderId && o.getOrderstatus().getId() == 5)
                    .findAny()
                    .orElse(null);

            assert order != null;
            order.setOrderstatus(orderStatusRepository.findById(4L).orElse(null));

            List<OrderDetail> orderDetails = orderDetailRepository.findAll()
                    .stream()
                    .filter(od -> od.getOrder().getId() == order.getId())
                    .toList();
            for (OrderDetail orderDetail : orderDetails) {
                Product product = orderDetail.getProduct();
                product.setQuantity(product.getQuantity() + orderDetail.getQuantity());
                productRepository.save(product);
            }

            orderRepository.save(order);
            return "redirect:/order/history/5";
        } catch (Exception ex) {
            return "error";
        }
    }

    private ListOrderDTO toListOrderDTO(Order order) {
        return new ListOrderDTO(order.getId(), orderDetailRepository.findAll()
                .stream()
                .filter(od -> od.getOrder().getId() == order.getId())
                .map(this::toOrderProductDTO)
                .collect(Collectors.toList()),
                order.getOrderstatus().getId(),
                order.getOrderstatus().getName(),
                order.getUser().getId(),
                order.getFirstname(),
                order.getLastName(),
                order.getAddress(),
                order.getUser().getEmail());
    }

    private OrderProductDTO toOrderProductDTO(OrderDetail orderdetail) {
        return new OrderProductDTO(orderdetail.getProduct().getImage(),
                orderdetail.getProduct().getProductName(),
                orderdetail.getQuantity(),
                orderdetail.getPrice());
    }
}
