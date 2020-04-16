package com.kodilla.ecommercee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ItemDto {
    private Long id;
    private Long cartId;
    private Long productId;
    private double quantity;
    private double price;
}
