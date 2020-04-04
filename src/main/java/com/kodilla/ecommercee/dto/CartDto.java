package com.kodilla.ecommercee.dto;

import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class CartDto {
    @NonNull
    private Long id;
    private List<ItemDto> items;
    private boolean closed = false;
    private OrderDto orderDto;
}