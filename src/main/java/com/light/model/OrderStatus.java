package com.light.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name = "`orderstatus`")
public class OrderStatus {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "bootstapicon")
    private String bootstapIcon;

    @OneToMany(mappedBy = "orderStatus")
    private Collection<Order> orders;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderStatus that = (OrderStatus) o;

        if (id != that.id) return false;
        if (!Objects.equals(name, that.name)) return false;
        return Objects.equals(bootstapIcon, that.bootstapIcon);
    }

    @Override
    public int hashCode() {
        int result = Long.hashCode(id);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (bootstapIcon != null ? bootstapIcon.hashCode() : 0);
        return result;
    }
}
