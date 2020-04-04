package com.kodilla.ecommercee.dto;

import lombok.*;

import java.time.LocalDate;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class OrderDto {
    @NonNull
    private Long Id;

    @NonNull
    private LocalDate orderMade;

    private boolean resolved;

    private boolean mailSend;

    @NonNull
    private UserDto user;

    @NonNull
    private CartDto cartDto;
}