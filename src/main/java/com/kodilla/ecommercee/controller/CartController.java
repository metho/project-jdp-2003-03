package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.CartDto;
import com.kodilla.ecommercee.entity.*;
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


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public Cart createCart(CartDto cartDto){
        return service.createCart(mapper.translateToCart(cartDto));
    }

    @GetMapping(value = "/{itemId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Item getItem(@PathVariable Long itemId) {
        return service.getItem(itemId);
    }

    @PutMapping(value = "/{itemId}/{cartId}")
    public void addItem(@PathVariable("itemId") Long itemId,@PathVariable("cartId") Long cartId){
        service.addItem(itemId,cartId);
    }

    @DeleteMapping(value = "/{itemId}/{cartId}")
    public void deleteItem(@PathVariable("itemId") Long itemId,@PathVariable("cartId") Long cartId){
        service.deleteItem(itemId,cartId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserOrder createAnOrder(@PathVariable CartDto cartDto){
        return service.createAnOrder(mapper.translateToCart(cartDto));
    }

}
