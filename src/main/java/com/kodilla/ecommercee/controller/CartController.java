package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.CartDto;
import com.kodilla.ecommercee.dto.ItemDto;
import com.kodilla.ecommercee.dto.OrderDto;
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
    private CartService service;

    @Autowired
    private CartMapper mapper;

    @Autowired
    private OrderMapper orderMapper;


    @PostMapping(path ="/newCart")
    public CartDto createCart(){
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

    @GetMapping("/item/{itemId}")
    public ItemDto getItem(@PathVariable Long itemId) {
        return mapper.mapToItemDto(service.getItem(itemId));
    }
    @PutMapping(path ="/updateItem", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ItemDto updateItem(@RequestBody ItemDto itemDto) {
        return mapper.mapToItemDto(service.saveItem(mapper.mapToItem(itemDto)));
    }

    @PostMapping(path ="/item", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addItem(@RequestBody ItemDto itemDto){
        service.addItem(mapper.mapToItem(itemDto));
    }

    @DeleteMapping("/item/{itemId}")
    public void deleteItem(@PathVariable("itemId") Long itemId){
        service.deleteItem(itemId);
    }

    @PostMapping(path ="/newOrder", consumes = MediaType.APPLICATION_JSON_VALUE)
    public OrderDto createAnOrder(OrderDto orderDto) {
        return orderMapper.mapToOrderDto(service.createAnOrder(orderDto));
    }

}
