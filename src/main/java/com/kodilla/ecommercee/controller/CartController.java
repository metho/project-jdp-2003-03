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


    @PostMapping(path ="/newCart", consumes = MediaType.APPLICATION_JSON_VALUE)
    public CartDto createCart(Cart cart){
        return mapper.mapToCartDto(service.createCart(cart));
    }

    @GetMapping("/{itemId}")
    public ItemDto getItem(@PathVariable Long itemId) {
        return mapper.mapToItemDto(service.getItem(itemId));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addItem(@RequestBody Item item){
        service.addItem(item);
    }

    @DeleteMapping("/{itemId}")
    public void deleteItem(@PathVariable("itemId") Long itemId){
        service.deleteItem(itemId);
    }

    @PostMapping(path ="/newOrder", consumes = MediaType.APPLICATION_JSON_VALUE)
    public OrderDto createAnOrder(OrderDto orderDto) throws CartNotFoundException, UserNotFoundException {
        return orderMapper.mapToOrderDto(service.createAnOrder(orderDto));
    }

}
