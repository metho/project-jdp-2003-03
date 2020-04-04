package com.kodilla.ecommercee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class ItemDto {
    @NonNull
    private Long id;

    private Long cartId;

    @NonNull
    private Long productId;

    @NonNull
    private double quantity;
}
