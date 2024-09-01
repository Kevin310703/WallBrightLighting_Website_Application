package com.light.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderStatusDTO {
    private long id;
    private String name;

    public OrderStatusDTO() {;}

    public OrderStatusDTO(long id, String name) {
        this.id = id;
        this.name = name;
    }

}
