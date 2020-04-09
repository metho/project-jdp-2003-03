package com.kodilla.ecommercee.dto;

import lombok.*;

import java.time.LocalDate;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private Long Id;

    private LocalDate orderMade;

    private boolean resolved;

    private boolean mailSend;

    private Long userId;

    private CartDto cartDto;
}