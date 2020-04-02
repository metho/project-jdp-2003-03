package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.CartDto;
import com.kodilla.ecommercee.entity.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;


@RestController
@RequestMapping("/v1/cart")
public class CartController {


    @PostMapping
    public Cart createCart(CartDto cartDto){
        return new Cart(1L,new ArrayList<Item>(),false);
    }
    @GetMapping(value = "/{productId}")
    public Product getProductFromCart(@PathVariable Long productId) {
        ProductGroup group = new ProductGroup(1L,"devices",new ArrayList<Product>());
        return new Product(productId, "Acer", 2.0, "none", "none", 2020, "Poland", "none", group, new ArrayList<>());
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
        return new UserOrder(1L,LocalDate.of(2020,03,30),false,new User(),new Cart(cartId,new ArrayList<Item>(),false));
    }

}
