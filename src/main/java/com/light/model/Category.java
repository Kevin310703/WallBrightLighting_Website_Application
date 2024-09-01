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
@Table(name = "`category`")
public class Category {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;

    @Basic
    @Column(name = "categoryName")
    private String categoryName;

    @Basic
    @Column(name = "description")
    private String description;

    @Basic
    @Column(name = "isActive")
    private Byte isActive;

    @Basic
    @Column(name = "createAt")
    private Timestamp createAt;

    @Basic
    @Column(name = "createBy")
    private String createBy;

    @Basic
    @Column(name = "modifyAt")
    private Timestamp modifyAt;

    @Basic
    @Column(name = "modifyBy")
    private String modifyBy;

    @OneToMany(mappedBy = "category")
    private Collection<Product> products;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        if (id != category.id) return false;
        if (!Objects.equals(categoryName, category.categoryName))
            return false;
        if (!Objects.equals(description, category.description))
            return false;
        if (!Objects.equals(isActive, category.isActive)) return false;
        if (!Objects.equals(createAt, category.createAt)) return false;
        if (!Objects.equals(createBy, category.createBy)) return false;
        if (!Objects.equals(modifyAt, category.modifyAt)) return false;
        return Objects.equals(modifyBy, category.modifyBy);
    }

    @Override
    public int hashCode() {
        int result = Long.hashCode(id);
        result = 31 * result + (categoryName != null ? categoryName.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (isActive != null ? isActive.hashCode() : 0);
        result = 31 * result + (createAt != null ? createAt.hashCode() : 0);
        result = 31 * result + (createBy != null ? createBy.hashCode() : 0);
        result = 31 * result + (modifyAt != null ? modifyAt.hashCode() : 0);
        result = 31 * result + (modifyBy != null ? modifyBy.hashCode() : 0);
        return result;
    }

}
