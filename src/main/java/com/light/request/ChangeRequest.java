package com.light.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ChangeRequest {
    private String currentPass;
    private String newPass;
    private String confirmNewPass;

    public ChangeRequest() {;}

    public ChangeRequest(String currentPass, String newPass, String confirmNewPass) {
        this.currentPass = currentPass;
        this.newPass = newPass;
        this.confirmNewPass = confirmNewPass;
    }
}
