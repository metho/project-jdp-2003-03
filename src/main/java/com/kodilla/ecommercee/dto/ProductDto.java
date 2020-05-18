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

}
