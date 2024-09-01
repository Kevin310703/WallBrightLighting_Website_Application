package com.light.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class ProductQuantityRequest {
    private long productID;
    private int quantity;
    private BigDecimal price;

    public ProductQuantityRequest() {;}

    public ProductQuantityRequest(long productID, int quantity, BigDecimal price) {
        this.productID = productID;
        this.quantity = quantity;
        this.price = price;
    }
}
