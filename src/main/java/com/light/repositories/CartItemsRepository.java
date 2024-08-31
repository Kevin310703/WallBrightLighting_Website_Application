package com.light.repositories;


import com.light.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemsRepository extends JpaRepository<CartItem, Long> {
}
