package com.kodilla.ecommercee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductGroupDto {
    private Long id;
    private String name;
    private String key;
}
