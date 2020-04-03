package com.kodilla.ecommercee.dto;

import com.kodilla.ecommercee.entity.Cart;
import com.kodilla.ecommercee.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {
    private Long id;
    private List<Item> items;
    private boolean closed = false;
    private OrderDto orderDto;
}