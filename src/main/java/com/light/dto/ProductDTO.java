package com.light.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class ProductDTO {
    private long id;
    private String productName;
    private long categoryId;
    private long statusId;
    private String categoryName;
    private String statusName;
    private BigDecimal price;
    private int quantity;
    private BigDecimal salePrice;
    private String image;
    private String description;

    public ProductDTO() {;}

    public ProductDTO(long id, String productName, String categoryName, String statusName, BigDecimal price, int quantity, BigDecimal salePrice, String image) {
        this.id = id;
        this.productName = productName;
        this.categoryName = categoryName;
        this.statusName = statusName;
        this.price = price;
        this.quantity = quantity;
        this.salePrice = salePrice;
        this.image = image;
    }

    public ProductDTO(long id, String productName, long categoryId, long statusId, String categoryName, String statusName, BigDecimal price, int quantity, BigDecimal salePrice, String image) {
        this.id = id;
        this.productName = productName;
        this.categoryId = categoryId;
        this.statusId = statusId;
        this.categoryName = categoryName;
        this.statusName = statusName;
        this.price = price;
        this.quantity = quantity;
        this.salePrice = salePrice;
        this.image = image;
    }

    public ProductDTO(long id, String productName, long categoryId, long statusId, String categoryName, String statusName, BigDecimal price, int quantity, BigDecimal salePrice, String image, String description) {
        this.id = id;
        this.productName = productName;
        this.categoryId = categoryId;
        this.statusId = statusId;
        this.categoryName = categoryName;
        this.statusName = statusName;
        this.price = price;
        this.quantity = quantity;
        this.salePrice = salePrice;
        this.image = image;
        this.description = description;
    }
}
