package com.light.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "`product`")
public class Product {
    @Setter
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;

    @Setter
    @Getter
    @Basic
    @Column(name = "productName")
    private String productName;

    @Setter
    @Getter
    @Basic
    @Column(name = "title")
    private String title;

    @Setter
    @Getter
    @Basic
    @Column(name = "price")
    private BigDecimal price;

    @Setter
    @Getter
    @Basic
    @Column(name = "description")
    private String description;

    @Setter
    @Getter
    @Basic
    @Column(name = "quantity")
    private Integer quantity;

    @Getter
    @Setter
    @Basic
    @Column(name = "isActive")
    private Byte isActive;

    @Getter
    @Setter
    @Basic
    @Column(name = "createAt")
    private Timestamp createAt;

    @Getter
    @Setter
    @Basic
    @Column(name = "createBy")
    private String createBy;

    @Getter
    @Setter
    @Basic
    @Column(name = "modifyAt")
    private Timestamp modifyAt;

    @Getter
    @Setter
    @Basic
    @Column(name = "modifyBy")
    private String modifyBy;

    @Getter
    @Setter
    @Basic
    @Column(name = "image")
    private String image;

    @Setter
    @Getter
    @Basic
    @Column(name = "saleprice")
    private BigDecimal salePrice;

    @Getter
    @Setter
    @OneToMany(mappedBy = "product")
    private Collection<CartItem> cartItems;

    @Setter
    @Getter
    @OneToMany(mappedBy = "product")
    private Collection<OrderDetail> orderDetails;

    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "categoryId", referencedColumnName = "id")
    private Category category;

    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "productStatusId", referencedColumnName = "id")
    private ProductStatus productstatus;

    @Setter
    @Getter
    @OneToMany(mappedBy = "product")
    private Collection<Rate> rates;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (id != product.id) return false;
        if (!Objects.equals(productName, product.productName)) return false;
        if (!Objects.equals(title, product.title)) return false;
        if (!Objects.equals(price, product.price)) return false;
        if (!Objects.equals(description, product.description)) return false;
        if (!Objects.equals(quantity, product.quantity)) return false;
        if (!Objects.equals(isActive, product.isActive)) return false;
        if (!Objects.equals(createAt, product.createAt)) return false;
        if (!Objects.equals(createBy, product.createBy)) return false;
        if (!Objects.equals(modifyAt, product.modifyAt)) return false;
        if (!Objects.equals(modifyBy, product.modifyBy)) return false;
        if (!Objects.equals(image, product.image)) return false;

        if (!Objects.equals(salePrice, product.salePrice)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = Long.hashCode(id);
        result = 31 * result + (productName != null ? productName.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        result = 31 * result + (isActive != null ? isActive.hashCode() : 0);
        result = 31 * result + (createAt != null ? createAt.hashCode() : 0);
        result = 31 * result + (createBy != null ? createBy.hashCode() : 0);
        result = 31 * result + (modifyAt != null ? modifyAt.hashCode() : 0);
        result = 31 * result + (modifyBy != null ? modifyBy.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (salePrice != null ? salePrice.hashCode() : 0);
        return result;
    }
}
