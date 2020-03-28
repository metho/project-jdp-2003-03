package com.kodilla.ecommercee.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {
    private Long id;
    private String name;
    private String description;

}
