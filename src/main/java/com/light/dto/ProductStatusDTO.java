package com.light.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductStatusDTO {
    private long id;
    private String name;

    public ProductStatusDTO(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public ProductStatusDTO() {;}
}
