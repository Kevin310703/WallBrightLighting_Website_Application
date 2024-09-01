package com.light.repositories;

import com.light.dto.ProfitDTO;
import com.light.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT new com.light.dto.ProfitDTO(" +
            "FUNCTION('MONTHNAME', o.orderDate), " +
            "COALESCE(SUM(t.total), 0)) " +
            "FROM Order o " +
            "LEFT JOIN o.transactions t " +  // Use JPA association path
            "GROUP BY FUNCTION('MONTH', o.orderDate), FUNCTION('MONTHNAME', o.orderDate) " +
            "ORDER BY FUNCTION('MONTH', o.orderDate)")
    List<ProfitDTO> getSummary();
}
