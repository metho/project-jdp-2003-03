package com.kodilla.ecommercee.dto;

import com.kodilla.ecommercee.entity.Item;
import com.kodilla.ecommercee.entity.ProductGroup;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
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
    private ProductGroup productGroup;
    private List<Item> items;
}
