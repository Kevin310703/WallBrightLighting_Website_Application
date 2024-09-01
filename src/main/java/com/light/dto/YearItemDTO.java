package com.light.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class YearItemDTO {
    private int value;
    private String text;

    public YearItemDTO() {;}

    public YearItemDTO(int value, String text) {
        this.value = value;
        this.text = text;
    }
}
