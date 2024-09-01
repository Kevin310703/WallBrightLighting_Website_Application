package com.light.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
public class AddProductRequest {
    private String productName;
    private String quantity;
    private String price;
    private long productStatusId;
    private String salePrice;
    private long categoryId;
    private MultipartFile image;
    private String description;


    public AddProductRequest(String productName, String quantity, String price, long productStatusId, String salePrice, long categoryId, MultipartFile image, String description) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.productStatusId = productStatusId;
        this.salePrice = salePrice;
        this.categoryId = categoryId;
        this.image = image;
        this.description = description;
    }

    public AddProductRequest() {;}
}
