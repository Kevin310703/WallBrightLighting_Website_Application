package com.light.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CategoryDTO {
    private long id;
    private String categoryName;
    private String description;
    private String image;

    public CategoryDTO(long id, String categoryName, String description, String image) {
        this.id = id;
        this.categoryName = categoryName;
        this.description = description;
        this.image = image;
    }

    public CategoryDTO(long id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }

    public CategoryDTO() {;}
}
