package com.kodilla.ecommercee.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private double price;
    private String brand;
    private String model;
    private int year;
    private String origin;
    private String description;

    @ManyToOne
    @JoinColumn
    private ProductGroup productGroup;

    @OneToMany(mappedBy = "product")
    private List<Item> items = new ArrayList<>();

    public Product(String name, double price, ProductGroup productGroup) {
        this.name = name;
        this.price = price;
        this.productGroup = productGroup;
    }

    public Product(Long id, String name, String description, double price, String brand, String model, String origin, int year) {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return year == product.year &&
                Objects.equals(id, product.id) &&
                Objects.equals(name, product.name) &&
                Objects.equals(brand, product.brand) &&
                Objects.equals(model, product.model) &&
                Objects.equals(origin, product.origin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, brand, model, year, origin);
    }
}
