package com.light.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String username;

    private String email;
    private String address;
    private String password;
    private String confirmPassword;
}
