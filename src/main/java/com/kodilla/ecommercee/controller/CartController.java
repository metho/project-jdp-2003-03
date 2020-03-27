package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.CartDto;
import com.kodilla.ecommercee.entity.Cart;
import com.kodilla.ecommercee.GenericEntity;
import com.kodilla.ecommercee.entity.UserOrder;
import com.kodilla.ecommercee.exception.CartNotFoundException;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/v1/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private CartMapper cartMapper;

    @PostMapping
    public Cart createCart(CartDto cartDto){
        return cartService.createCart(cartMapper.translateToCart(cartDto));
    }
    @GetMapping(value = "/{cartId}")
    public Optional<GenericEntity> getProductFromCart(@PathVariable Long cartId, int productId) throws CartNotFoundException {
        return Optional.ofNullable(cartService.getProductFromCart(cartId, productId).orElseThrow(CartNotFoundException::new));
    }
    @PutMapping(value = "/{cartId}")
    public boolean addProductToCart(@PathVariable Long cartId,GenericEntity product){
        return cartService.addProductToCart(cartId, product);
    }
    @PutMapping
    public void deleteProductFromCart(@PathVariable Long cartId, int productId){
        cartService.deleteProductFromCart(cartId, productId);
    }
    @PostMapping
    public UserOrder createAnOrder(@PathVariable Long cartId){
        return cartService.createAnOrderFromCart(cartMapper.translateToUserOrder(cartService.getCart(cartId)));
    }

}
