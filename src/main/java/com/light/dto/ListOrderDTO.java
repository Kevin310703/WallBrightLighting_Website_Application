package com.light.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ListOrderDTO {
    private long id;
    private List<OrderProductDTO> listProduct;
    private long statusId;
    private String statusName;
    private long userId;
    private String firstName;
    private String lastName;
    private String address;
    private String email;

    public ListOrderDTO() {;}

    public ListOrderDTO(long id, List<OrderProductDTO> listProduct, long statusId, String statusName, long userId, String firstName, String lastName, String address, String email) {
        this.id = id;
        this.listProduct = listProduct;
        this.statusId = statusId;
        this.statusName = statusName;
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
    }
}
