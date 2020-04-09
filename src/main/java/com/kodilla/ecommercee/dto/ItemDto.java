package com.kodilla.ecommercee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ItemDto {

    private Long id;

    private Long carId;

    private Long productId;

    private double quantity;

    private double price;

}
