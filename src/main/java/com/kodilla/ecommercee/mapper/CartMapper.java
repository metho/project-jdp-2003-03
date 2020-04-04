package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.dto.CartDto;
import com.kodilla.ecommercee.dto.ItemDto;
import com.kodilla.ecommercee.entity.Cart;
import com.kodilla.ecommercee.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class CartMapper {

    @Autowired
    OrderMapper orderMapper;



    public Cart mapToCart(CartDto cartDto) {
        return new Cart(cartDto.getId());
    }

    public CartDto mapToCartDto(Cart cart) {
        return new CartDto(cart.getId());
    }

    public ItemDto mapToItemDto(Item item){
        return new ItemDto(item.getId(),item.getProduct().getId(),item.getQuantity());
    }

}
