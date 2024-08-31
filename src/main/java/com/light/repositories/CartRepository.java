package com.light.repositories;


import com.light.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface CartRepository extends JpaRepository<Cart, Long> {
    @Query ("SELECT SUM(ci.quantity * (CASE WHEN p.saleprice IS NOT NULL THEN p.saleprice ELSE p.price END)) FROM Cart c JOIN c.cartitems ci JOIN ci.product p WHERE c.id = :cartId")
    BigDecimal getTotal(@Param("cartId") Long cartId);

}
