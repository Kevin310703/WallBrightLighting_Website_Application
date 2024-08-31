package com.light.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name = "`feedback`")
public class Feedback {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "comment")
    private String comment;
    @Basic
    @Column(name = "rate")
    private Short rate;
    @Basic
    @Column(name = "createAt")
    private Timestamp createdAt;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "name")
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Feedback feedback = (Feedback) o;

        if (id != feedback.id) return false;
        if (!Objects.equals(comment, feedback.comment)) return false;
        if (!Objects.equals(rate, feedback.rate)) return false;
        if (!Objects.equals(createdAt, feedback.createdAt)) return false;
        if (!Objects.equals(email, feedback.email)) return false;
        return Objects.equals(name, feedback.name);
    }

    @Override
    public int hashCode() {
        int result = Long.hashCode(id);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (rate != null ? rate.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
