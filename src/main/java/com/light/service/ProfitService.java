package com.light.service;

import com.light.dto.ProfitDTO;
import com.light.model.Order;
import com.light.model.Transaction;
import com.light.repositories.OrderRepository;
import com.light.repositories.TransactionRepository;

import java.math.BigDecimal;
import java.time.Month;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProfitService {
    private final OrderRepository orderRepository;

    public ProfitService(TransactionRepository transactionRepository, OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<ProfitDTO> getSummary() {
        // Fetch all orders
        List<Order> orders = orderRepository.findAll();

        // Group by month and calculate the total
        Map<String, BigDecimal> profitByMonth = orders.stream()
                .collect(Collectors.groupingBy(
                        order -> Month.of(order.getOrderDate().getMonth()).name(),
                        Collectors.reducing(BigDecimal.ZERO,
                                order -> order.getTransactions().stream()
                                        .map(Transaction::getTotal)
                                        .reduce(BigDecimal.ZERO, BigDecimal::add),
                                BigDecimal::add)
                ));

        // Map to ProfitDTO
        return profitByMonth.entrySet().stream()
                .map(entry -> new ProfitDTO(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }
}
