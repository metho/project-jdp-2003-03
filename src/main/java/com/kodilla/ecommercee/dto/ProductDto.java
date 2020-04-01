package com.kodilla.ecommercee.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
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

    public ProductDto(Long id, String name, String description, String brand, String model, String origin, double price, int year) {
    }
}
