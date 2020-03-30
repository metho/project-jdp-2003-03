package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.CartDto;
import com.kodilla.ecommercee.entity.*;
import com.kodilla.ecommercee.exception.CartNotFoundException;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
@RequestMapping("/v1/cart")
public class CartController {

//    @Autowired
//    private CartService cartService;
//
//    @Autowired
//    private CartMapper cartMapper;


    @PostMapping
    public Cart createCart(CartDto cartDto){
        return new Cart(1L,new ArrayList<Item>(),false);
    }
    @GetMapping(value = "/{productId}")
    public Product getProductFromCart(@PathVariable Long productId) {
        return new Product(productId,new ProductGroup(1L,"devices",new ArrayList<Product>()),new ArrayList<Item>());
    }
    @PutMapping(value = "/{productId}")
    public Cart addProductToCart(@PathVariable Long productId){
        return new Cart(1L,new ArrayList<Item>(),false);
    }
    @DeleteMapping(value = "/{productId}")
    public void deleteProductFromCart(@PathVariable Long productId){
    }

    @PostMapping(value = "/{cartId}")
    public UserOrder createAnOrder(@PathVariable Long cartId){
        return new UserOrder(1L,new Cart(cartId,new ArrayList<Item>(),false));
    }

}
