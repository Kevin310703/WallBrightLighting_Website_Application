package com.light.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddtoCartRequest {
    private long productId;
    private int quantity;

    public AddtoCartRequest() {;}

    public AddtoCartRequest(long productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

}
