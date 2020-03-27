package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.dto.CartDto;
import com.kodilla.ecommercee.entity.Cart;
import com.kodilla.ecommercee.entity.UserOrder;
import org.springframework.stereotype.Component;

@Component
public class CartMapper {

    public Cart translateToCart(CartDto cartDto) {
        return new Cart(cartDto.getId(),cartDto.getProducts());
    }

    public UserOrder translateToUserOrder(Cart cart) {
        return new UserOrder();
    }
}
