package com.kodilla.ecommercee.controller;
import com.kodilla.ecommercee.entity.Cart;
import com.kodilla.ecommercee.entity.Product;
import com.kodilla.ecommercee.entity.UserOrder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/v1/cart")
public class CartController {


    @PostMapping
    public Cart createCart(Cart cart) {
        return new Cart(1L);
    }

    @GetMapping("{/productId}")
    public Product getProductFromCart(@PathVariable Long productId) {
        return new Product(1L, "laptop", "DELL");
    }

    @PutMapping("{/productId}")
    public Cart addProductToCart(@PathVariable Long productId) {
        return new Cart(1L);
    }

    @DeleteMapping("{/productId}")
    public void deleteProductFromCart(@PathVariable Long productId) {
    }

    @PostMapping
    public UserOrder createAnOrder(Cart cart) {
        return new UserOrder(14L, LocalDate.of(2020, 03, 29), false);
    }

}