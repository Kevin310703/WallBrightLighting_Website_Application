package com.light.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name = "`cartitem`")
public class CartItem {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;

    @Basic
    @Column(name = "quantity")
    private Integer quantity;
    @ManyToOne
    @JoinColumn(name = "productID", referencedColumnName = "id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "cartID", referencedColumnName = "id")
    private Cart cart;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CartItem cartitem = (CartItem) o;

        if (id != cartitem.id) return false;
        return Objects.equals(quantity, cartitem.quantity);
    }

    @Override
    public int hashCode() {
        int result = Long.hashCode(id);
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        return result;
    }
}
