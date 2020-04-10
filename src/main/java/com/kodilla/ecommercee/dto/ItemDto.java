package com.kodilla.ecommercee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class ItemDto {

    private Long id;
    @NonNull
    private CartDto cartId;

    @NonNull
    private ProductDto productId;

    private double quantity;

    private double price;
}
