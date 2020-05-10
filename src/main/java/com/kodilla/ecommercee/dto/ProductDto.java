package com.kodilla.ecommercee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private double price;
    private String brand;
    private String model;
    private int year;
    private String origin;
    private String description;
    private Long groupId;


    public ProductDto(Long id, String name, double price, String brand, String model,
                      int year, String origin, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.origin = origin;
        this.description = description;
    }
}
