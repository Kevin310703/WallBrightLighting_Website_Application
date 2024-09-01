package com.light.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class ProfitDTO {
    private String month;
    private BigDecimal total;

    public ProfitDTO() {;}

    public ProfitDTO(String month, BigDecimal total) {
        this.month = month;
        this.total = total;
    }
}
