package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.CartDto;
import com.kodilla.ecommercee.dto.ItemDto;
import com.kodilla.ecommercee.dto.OrderDto;
import com.kodilla.ecommercee.entity.*;
import com.kodilla.ecommercee.exception.CartNotFoundException;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.mapper.OrderMapper;
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

    @Autowired
    OrderMapper orderMapper;


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public CartDto createCart(CartDto cartDto){
        return mapper.mapToCartDto(service.createCart(mapper.mapToCart(cartDto)));
    }

    @GetMapping("/{itemId}")
    public ItemDto getItem(@PathVariable Long itemId) {
        return mapper.mapToItemDto(service.getItem(itemId));
    }

    @PostMapping(value = "/{cartId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addItem(@RequestBody Item item, @PathVariable("cartId") Long cartId){
        service.addItem(item,cartId);
    }

    @DeleteMapping("/{itemId}")
    public void deleteItem(@PathVariable("itemId") Long itemId){
        service.deleteItem(itemId);
    }

    @PostMapping("/{cartId}/{userId}")
    public OrderDto createAnOrder(@PathVariable("cartId") Long cartId, @PathVariable("userId") Long userId) throws CartNotFoundException, UserNotFoundException {
        return orderMapper.translateToOrderDto(service.createAnOrder(cartId, userId));
    }

}
