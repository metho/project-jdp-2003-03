package com.kodilla.ecommercee.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(unique = true)
    private Long id;

    @Column
    @NotNull
    private String name;

    @NotNull
    @Column
    private double price;

    @Column
    private String brand;

    @Column
    private String model;

    @Column(name = "Production_year")
    private int year;

    @Column(name = "Origin_country")
    private String origin;

    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "product_group_id", nullable = false)
    private ProductGroup productGroup;

    @OneToMany(mappedBy = "product")
    private List<Item> items = new ArrayList<>();

    public void setProductGroup(ProductGroup productGroup) {
        this.productGroup = productGroup;
    }
}
