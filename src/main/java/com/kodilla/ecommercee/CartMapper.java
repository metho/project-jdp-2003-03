package com.kodilla.ecommercee;

public class CartMapper {

    public Cart translateToCart(CartDto cartDto) {
        return new Cart(cartDto.getId());
    }

    public CartDto translateToCartDto(Cart cart) {
        return new CartDto(cart.getId());
    }
}
