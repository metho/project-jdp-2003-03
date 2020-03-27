package com.kodilla.ecommercee.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ItemDto {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("cart_id")
    private Long cartId;
    @JsonProperty("product_id")
    private Long productId;
    @JsonProperty("quantity")
    private double quantity;
    @JsonProperty("key")
    private String key;
}
