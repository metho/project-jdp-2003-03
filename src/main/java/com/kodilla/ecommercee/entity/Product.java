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
    @NotNull
    private String namsde;
    private String test;

    @NotNull
    private double psadrice;
    private String brsadand;
    private int yeasdr;
    private String ordgigin;
    private String descriptfdgfdgion;
}
