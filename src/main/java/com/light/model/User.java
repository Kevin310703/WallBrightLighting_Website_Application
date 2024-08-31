package com.light.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name = "`user`")
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "phone")
    private String phone;
    @Basic
    @Column(name = "username")
    private String username;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "firstName")
    private String firstName;
    @Basic
    @Column(name = "lastName")
    private String lastName;
    @Basic
    @Column(name = "dob")
    private Timestamp dob;
    @Basic
    @Column(name = "address")
    private String address;
    @Basic
    @Column(name = "verifyCode")
    private String verifyCode;
    @Basic
    @Column(name = "verifyAt")
    private Timestamp verifyAt;
    @Basic
    @Column(name = "isActive")
    private Byte isActive;
    @Basic
    @Column(name = "createAt")
    private Timestamp createAt;
    @OneToMany(mappedBy = "user")
    private Collection<Cart> carts;
    @OneToMany(mappedBy = "user")
    private Collection<Order> orders;
    @OneToMany(mappedBy = "user")
    private Collection<Rate> rates;
    @ManyToOne
    @JoinColumn(name = "RoleId", referencedColumnName = "id")
    private Role role;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (!Objects.equals(email, user.email)) return false;
        if (!Objects.equals(phone, user.phone)) return false;
        if (!Objects.equals(username, user.username)) return false;
        if (!Objects.equals(password, user.password)) return false;
        if (!Objects.equals(firstName, user.firstName)) return false;
        if (!Objects.equals(lastName, user.lastName)) return false;
        if (!Objects.equals(dob, user.dob)) return false;
        if (!Objects.equals(address, user.address)) return false;
        if (!Objects.equals(verifyCode, user.verifyCode)) return false;
        if (!Objects.equals(verifyAt, user.verifyAt)) return false;
        if (!Objects.equals(isActive, user.isActive)) return false;
        return Objects.equals(createAt, user.createAt);
    }

    @Override
    public int hashCode() {
        int result = Long.hashCode(id);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (dob != null ? dob.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (verifyCode != null ? verifyCode.hashCode() : 0);
        result = 31 * result + (verifyAt != null ? verifyAt.hashCode() : 0);
        result = 31 * result + (isActive != null ? isActive.hashCode() : 0);
        result = 31 * result + (createAt != null ? createAt.hashCode() : 0);
        return result;
    }

}
