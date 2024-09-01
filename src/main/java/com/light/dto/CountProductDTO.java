package com.light.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CountProductDTO {
    private int count;
    private String productName;
    private long productId;

    public CountProductDTO() {;}

    public CountProductDTO(int count, String productName, long productId) {
        this.count = count;
        this.productName = productName;
        this.productId = productId;
    }
}
