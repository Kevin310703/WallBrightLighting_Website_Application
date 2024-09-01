package com.light.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FeedbackRequest {
    private String rate;
    private String name;
    private String email;
    private String comment;

    public FeedbackRequest() {;}

    public FeedbackRequest(String rate, String name, String email, String comment) {
        this.rate = rate;
        this.name = name;
        this.email = email;
        this.comment = comment;
    }
}
