package com.light.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class CartItemDTO {
    private long id;
    private long productId;
    private String productName;
    private int quantity;
    private BigDecimal price;
    private String productImage;

    public CartItemDTO() {;}

    public CartItemDTO(long id, long productId, String productName, int quantity, BigDecimal price, String productImage) {
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.productImage = productImage;
    }
}
