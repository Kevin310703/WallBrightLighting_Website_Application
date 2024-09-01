package com.light.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FeedbackDTO {
    private long id;
    private String name;
    private String comment;
    private String email;
    private Short rate;

    public FeedbackDTO(String name, String comment) {
        this.name = name;
        this.comment = comment;
    }

    public FeedbackDTO(String name, String comment, String email, Short rate) {
        this.name = name;
        this.comment = comment;
        this.email = email;
        this.rate = rate;
    }

    public FeedbackDTO(long id, String name, String comment, String email, Short rate) {
        this.id = id;
        this.name = name;
        this.comment = comment;
        this.email = email;
        this.rate = rate;
    }

    public FeedbackDTO() {;}

}
