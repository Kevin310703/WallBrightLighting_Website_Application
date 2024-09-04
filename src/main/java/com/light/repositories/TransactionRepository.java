package com.light.repositories;

import com.light.dto.ProfitDTO;
import com.light.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
//    @Query("SELECT new com.light.dto.ProfitDTO(" +
//            "FUNCTION('MONTHNAME', o.orderDate) as month, " +
//            "COALESCE(SUM(t.total), 0) as total) " +
//            "FROM Order o " +
//            "LEFT JOIN o.transactions t " +
//            "GROUP BY FUNCTION('MONTH', o.orderDate), FUNCTION('MONTHNAME', o.orderDate) " +
//            "ORDER BY FUNCTION('MONTH', o.orderDate)")
//    @Query("SELECT NEW com.light.dto.ProfitDTO(FUNCTION('MONTHNAME', o.orderDate), COALESCE(SUM(t.total), 0)) " +
//            "FROM Order o " +
//            "LEFT JOIN Transaction t ON o.id = t.order.id " +
//            "GROUP BY FUNCTION('MONTHNAME', o.orderDate), FUNCTION('MONTH', o.orderDate) " +
//            "ORDER BY FUNCTION('MONTH', o.orderDate)")
//    List<ProfitDTO> getSummary();

}
