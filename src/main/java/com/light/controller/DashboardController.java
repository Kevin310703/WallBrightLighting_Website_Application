package com.light.controller;

import com.light.dto.*;

import com.light.model.Order;
import com.light.model.Transaction;
import com.light.model.User;
import com.light.repositories.*;
import com.light.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CommonService commonService;

    @GetMapping("/index")
    public String index(@RequestParam("year") int year, @RequestParam("month") int month, Model model){
        User currentUser = commonService.getCurrentUser();
        if (currentUser == null || currentUser.getRole().getId() != 1){
            return "redirect:/auth/login";
        }

        int currentYear = Year.now().getValue();

        List<MonthItemDTO> months = IntStream.rangeClosed(1, 12)
                .mapToObj(i -> new MonthItemDTO(i, Month.of(i).getDisplayName(TextStyle.FULL, Locale.getDefault())))
                .collect(Collectors.toList());

        List<YearItemDTO> years = IntStream.rangeClosed(2000, currentYear)
                .mapToObj(i -> new YearItemDTO(i, String.valueOf(i)))
                .collect(Collectors.toList());

        List<CountProductDTO> data = productRepository.getProductSummary(year, month);

        List<String> labels = data.stream()
                .map(CountProductDTO::getProductName)
                .collect(Collectors.toList());

        List<Integer> values = data.stream()
                .map(CountProductDTO::getCount)
                .collect(Collectors.toList());
        model.addAttribute("currentMonth", month);
        model.addAttribute("currentYear", year);
        model.addAttribute("months", months);
        model.addAttribute("years", years);
        model.addAttribute("labels", labels);
        model.addAttribute("values", values);

        return "productsummary";
    }

    @GetMapping("/topCategories")
    public String topCategories(@RequestParam("year") int year, @RequestParam("month") int month, Model model){
        User currentUser = commonService.getCurrentUser();
        if (currentUser == null || currentUser.getRole().getId() != 1){
            return "redirect:/auth/login";
        }
        int currentYear = Year.now().getValue();

        List<MonthItemDTO> months = IntStream.rangeClosed(1, 12)
                .mapToObj(i -> new MonthItemDTO(i, Month.of(i).getDisplayName(TextStyle.FULL, Locale.getDefault())))
                .collect(Collectors.toList());

        List<YearItemDTO> years = IntStream.rangeClosed(2000, currentYear)
                .mapToObj(i -> new YearItemDTO(i, String.valueOf(i)))
                .collect(Collectors.toList());

        List<CountCategoryDTO> data = categoryRepository.getSummary(year, month);

        List<String> labels = data.stream()
                .map(CountCategoryDTO::getCategoryName)
                .collect(Collectors.toList());

        List<Integer> values = data.stream()
                .map(CountCategoryDTO::getTotal)
                .collect(Collectors.toList());
        model.addAttribute("currentMonth", month);
        model.addAttribute("currentYear", year);
        model.addAttribute("months", months);
        model.addAttribute("years", years);
        model.addAttribute("labels", labels);
        model.addAttribute("values", values);

        return "categorysummary";
    }

    @GetMapping("/profit")
    public String profit(@RequestParam("year") int year, Model model){
        User currentUser = commonService.getCurrentUser();
        if (currentUser == null || currentUser.getRole().getId() != 1){
            return "redirect:/auth/login";
        }
        int currentYear = Year.now().getValue();

        List<YearItemDTO> years = IntStream.rangeClosed(2000, currentYear)
                .mapToObj(i -> new YearItemDTO(i, String.valueOf(i)))
                .collect(Collectors.toList());

        List<MonthItemDTO> months = IntStream.rangeClosed(1, 12)
                .mapToObj(i -> new MonthItemDTO(i, YearMonth.of(2000, i).getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault())))
                .collect(Collectors.toList());

        YearMonth startDate = YearMonth.of(year, 1);
        YearMonth endDate = YearMonth.of(year, 12);

        List<YearMonth> yearMonths = new ArrayList<>();
        YearMonth currentMonth = startDate;
        while (!currentMonth.isAfter(endDate)) {
            yearMonths.add(currentMonth);
            currentMonth = currentMonth.plusMonths(1);
        }

        List<ProfitDTO> data = yearMonths.stream()
                .map(month -> {
                    LocalDate monthStart = month.atDay(1);
                    LocalDate monthEnd = month.atEndOfMonth();
                    List<Order> orders = orderRepository.findAll()
                            .stream()
                            .filter(order -> {
                                LocalDate orderDate = order.getOrderDate().toLocalDateTime().toLocalDate();
                                return !orderDate.isBefore(monthStart) && !orderDate.isAfter(monthEnd);
                            })
                            .toList();

                    List<Transaction> transactions = transactionRepository.findAll()
                            .stream()
                            .filter(transaction -> orders.stream().anyMatch(order -> order.getId() == transaction.getOrder().getId()))
                            .toList();
                    BigDecimal total = transactions.stream().map(Transaction::getTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
                    return new ProfitDTO(month.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault()), total);
                })
                .toList();

        List<String> labels = data.stream().map(ProfitDTO::getMonth).collect(Collectors.toList());
        List<BigDecimal> values = data.stream().map(ProfitDTO::getTotal).collect(Collectors.toList());

        model.addAttribute("currentYear", year);
        model.addAttribute("years", years);
        model.addAttribute("months", months);
        model.addAttribute("labels", labels);
        model.addAttribute("values", values);

        return "profit";
    }
}
