package com.kodilla.ecommercee.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
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
    @JoinColumn(name = "product_group_id", nullable = false)
    private ProductGroup productGroup;

    @OneToMany(mappedBy = "product")
    private List<Item> items = new ArrayList<>();

    public Product(Long id, String name, String description, double price, String brand, String model, String origin, int year) {
    }

    public Product(Long productId, ProductGroup devices, ArrayList<Item> items) {
        this.id = productId;
        this.productGroup = devices;
        this.items = items;
    }

    public void setProductGroup(ProductGroup productGroup) {
        this.productGroup = productGroup;
    }

    public void setName(String name) {
        this.name = name;
    }



    public Product(ProductGroup productGroup) {
        this.productGroup=productGroup;
    }

}
