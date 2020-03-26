package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.dto.CartDto;
import com.kodilla.ecommercee.entity.Cart;
import org.springframework.stereotype.Component;

@Component
public class CartMapper {

    public Cart translateToCart(CartDto cartDto) {
        return new Cart(cartDto.getId());
    }

    public CartDto translateToCartDto(Cart cart) {
        return new CartDto(cart.getId());
    }
}
