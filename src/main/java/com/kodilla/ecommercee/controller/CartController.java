package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.CartDto;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/cart")
public class CartController {

    @Autowired
    CartService service;

    @Autowired
    CartMapper mapper;

    @PostMapping(path = "/newCart")
    public CartDto createCart() {
        return mapper.mapToCartDto(service.createCart());
    }

    @GetMapping("/{cartId}")
    public CartDto getCart(@PathVariable Long cartId) {
        return mapper.mapToCartDto(service.getCart(cartId));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public CartDto updateCart(@RequestBody CartDto cartDto) {
        return mapper.mapToCartDto(service.saveCart(mapper.mapToCart(cartDto)));
    }

    @DeleteMapping("/{cartId}")
    public void deleteCart(@PathVariable Long cartId) {
        service.deleteCart(cartId);
    }


}
