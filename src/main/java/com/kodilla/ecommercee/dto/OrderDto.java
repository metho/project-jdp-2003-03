package com.kodilla.ecommercee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private LocalDate orderMade;
    private boolean resolved;
    private boolean mailSend;
    private Long userId;
    private Long cartId;
}