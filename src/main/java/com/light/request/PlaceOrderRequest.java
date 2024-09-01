package com.light.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class PlaceOrderRequest {
    private List<ProductQuantityRequest> productInfor;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private int payment;
    private String note;

    public PlaceOrderRequest() {;}

    public PlaceOrderRequest(List<ProductQuantityRequest> productInfor, String firstName, String lastName, String address, String phone, int payment, String note) {
        this.productInfor = productInfor;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.payment = payment;
        this.note = note;
    }
}
