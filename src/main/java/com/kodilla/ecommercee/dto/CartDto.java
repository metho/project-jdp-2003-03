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
    @NonNull
    private UserDto user;
    @NonNull
    private List<ItemDto> items;
    @NonNull
    private boolean closed = false;

}