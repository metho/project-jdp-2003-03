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
public class UserOrderDto {

    private Long Id;

    private LocalDate orderMade;

    private boolean resolved;

//    private User user;

//    private List<ProductDto> products;
}
