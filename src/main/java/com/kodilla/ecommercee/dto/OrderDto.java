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

    private boolean mailSend;

    private Long userId;

    private Long cartId;

    public OrderDto(Long id, LocalDate orderMade, boolean resolved, Long userId, Long cartId, boolean mailSend) {
        this.id = id;
        this.orderMade = orderMade;
        this.resolved = resolved;
        this.mailSend = mailSend;
        this.userId = userId;
        this.cartId = cartId;
    }
}