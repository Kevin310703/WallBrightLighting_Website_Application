package com.light.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "`order`")
public class Order {
    @Setter
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;

    @Setter
    @Getter
    @Basic
    @Column(name = "orderDate")
    private Timestamp orderDate;

    @Setter
    @Getter
    @Basic
    @Column(name = "notes")
    private String notes;

    @Setter
    @Getter
    @Basic
    @Column(name = "firstname")
    private String firstname;

    @Setter
    @Getter
    @Basic
    @Column(name = "lastName")
    private String lastName;

    @Setter
    @Getter
    @Basic
    @Column(name = "address")
    private String address;

    @Setter
    @Getter
    @Basic
    @Column(name = "phone")
    private String phone;

    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;

    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "orderStatusId", referencedColumnName = "id")
    private OrderStatus orderstatus;

    @OneToMany(mappedBy = "order")
    private Collection<OrderDetail> orderDetails;

    @Getter
    @OneToMany(mappedBy = "order")
    private Collection<Transaction> transactions;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != order.id) return false;
        if (!Objects.equals(orderDate, order.orderDate)) return false;
        if (!Objects.equals(notes, order.notes)) return false;
        if (!Objects.equals(firstname, order.firstname)) return false;
        if (!Objects.equals(lastName, order.lastName)) return false;
        if (!Objects.equals(address, order.address)) return false;
        if (!Objects.equals(phone, order.phone)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = Long.hashCode(id);
        result = 31 * result + (orderDate != null ? orderDate.hashCode() : 0);
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        return result;
    }

    public Collection<OrderDetail> getOrderdetails() {
        return orderDetails;
    }

    public void setOrderdetails(Collection<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public void setTransactions(Collection<Transaction> transactions) {
        this.transactions = transactions;
    }
}
