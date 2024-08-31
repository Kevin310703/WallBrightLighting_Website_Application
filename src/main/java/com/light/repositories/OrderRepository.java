package com.light.repositories;


import com.light.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT SUM(od.quantity * (CASE WHEN p.saleprice IS NOT NULL THEN p.saleprice ELSE p.price END)) FROM Order o JOIN o.orderdetails od JOIN od.product p WHERE o.id = :orderId")
    BigDecimal getTotal(@Param("orderId") Long orderId);
}
