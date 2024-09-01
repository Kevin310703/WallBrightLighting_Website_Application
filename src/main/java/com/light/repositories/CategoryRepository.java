package com.light.repositories;

import com.light.dto.CountCategoryDTO;
import com.light.model.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("SELECT NEW com.light.dto.CountCategoryDTO(c.categoryName, c.id, CAST(SUM(od.quantity) AS INTEGER)) " +
            "FROM Order o " +
            "JOIN o.orderDetails od " +
            "JOIN od.product p " +
            "JOIN p.category c " +
            "WHERE FUNCTION('YEAR', o.orderDate) = :year AND FUNCTION('MONTH', o.orderDate) = :month AND o.orderStatus.id <> 4 " +
            "GROUP BY c.id, c.categoryName " +
            "ORDER BY SUM(od.quantity) DESC")
    List<CountCategoryDTO> getSummary(@Param("year") int year, @Param("month") int month);

}
