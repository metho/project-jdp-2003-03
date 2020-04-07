package com.kodilla.ecommercee.dto;
import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class CartDto {

    private Long id;

    @NonNull
    private List<ItemDto> items;

    private boolean closed = false;

}