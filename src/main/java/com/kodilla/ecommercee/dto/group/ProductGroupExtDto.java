package com.kodilla.ecommercee.dto.group;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ProductGroupExtDto {
    private Long id;
    private String name;
    private List<ProductGroupLinkDto> links;
}
