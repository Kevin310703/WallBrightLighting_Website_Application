package com.light.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CountCategoryDTO {
    private String categoryName;
    private long id;
    private int total;

    public CountCategoryDTO() {;}

    public CountCategoryDTO(String categoryName, long id, int total) {
        this.categoryName = categoryName;
        this.id = id;
        this.total = total;
    }
}
