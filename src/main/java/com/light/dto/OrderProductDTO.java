package com.light.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class OrderProductDTO {
    private String image;
    private String productName;
    private int quantity;
    private BigDecimal price;

    public OrderProductDTO() {;}

    public OrderProductDTO(String image, String productName, int quantity, BigDecimal price) {
        this.image = image;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }
}
