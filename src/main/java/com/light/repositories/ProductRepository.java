package com.light.repositories;

import com.light.dto.CountProductDTO;
import com.light.model.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT NEW com.light.dto.CountProductDTO(CAST(SUM(od.quantity) AS INTEGER), p.productName, p.id) " +
            "FROM Order o " +
            "JOIN o.orderDetails od " +
            "JOIN od.product p " +
            "WHERE o.orderStatus.id <> 4 AND FUNCTION('YEAR', o.orderDate) = :year AND FUNCTION('MONTH', o.orderDate) = :month " +
            "GROUP BY p.id, p.productName " +
            "ORDER BY SUM(od.quantity) DESC")
    List<CountProductDTO> getProductSummary(@Param("year") int year, @Param("month") int month);
}
