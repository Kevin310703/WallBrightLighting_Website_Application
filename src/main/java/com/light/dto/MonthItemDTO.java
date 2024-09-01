package com.light.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MonthItemDTO {
    private int value;
    private String text;

    public MonthItemDTO() {;}

    public MonthItemDTO(int value, String text) {
        this.value = value;
        this.text = text;
    }

}
