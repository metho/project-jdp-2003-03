package com.kodilla.ecommercee.dto;

import com.kodilla.ecommercee.entity.Product;
import com.kodilla.ecommercee.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private Long Id;

    private LocalDate orderMade;

    private boolean resolved;

    private User userId;

    private List<Product> products;
}
