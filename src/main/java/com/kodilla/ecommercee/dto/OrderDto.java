package com.kodilla.ecommercee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private Long id;
    private LocalDate orderMade;
    private boolean resolved;
    private boolean mailSent;
    private UserDto user;
    private CartDto cartDto;

    public OrderDto(Long id, LocalDate orderMade, boolean resolved, UserDto user, CartDto cartDto, boolean mailSent) {
        this.id = id;
        this.orderMade = orderMade;
        this.resolved = resolved;
        this.mailSent = mailSent;
        this.user = user;
        this.cartDto = cartDto;
    }
}