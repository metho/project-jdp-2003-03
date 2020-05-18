package com.kodilla.ecommercee.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    public Product(Long id, String name, double price, String brand, String model, int year, String origin, String description, ProductGroup productGroup) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.origin = origin;
        this.description = description;
        this.productGroup=productGroup;
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
