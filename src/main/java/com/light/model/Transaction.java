package com.light.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name = "`transaction`")
public class Transaction {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "total")
    private BigDecimal total;
    @ManyToOne
    @JoinColumn(name = "orderID", referencedColumnName = "id")
    private Order order;
    @ManyToOne
    @JoinColumn(name = "paymentID", referencedColumnName = "id")
    private Payment payment;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transaction that = (Transaction) o;

        if (id != that.id) return false;
        if (!Objects.equals(total, that.total)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = Long.hashCode(id);
        result = 31 * result + (total != null ? total.hashCode() : 0);
        return result;
    }
}
