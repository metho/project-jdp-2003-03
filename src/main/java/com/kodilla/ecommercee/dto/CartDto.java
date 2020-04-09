package com.kodilla.ecommercee.dto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {

    private Long id;

    private List<ItemDto> items;

    private boolean closed = false;

}