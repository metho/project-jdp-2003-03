package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.dto.CartDto;
import com.kodilla.ecommercee.dto.ItemDto;
import com.kodilla.ecommercee.dto.OrderDto;
import com.kodilla.ecommercee.entity.Cart;
import com.kodilla.ecommercee.entity.Item;
import com.kodilla.ecommercee.entity.UserOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;


@Component
public class CartMapper {

    @Autowired
    OrderMapper orderMapper;

    public Cart translateToCart(CartDto cartDto) {
        return new Cart(cartDto.getId(),cartDto.getItems(),cartDto.isClosed(),orderMapper.translateToOrder(cartDto.getOrderDto()));
    }

    public CartDto translateToCartDto(Cart cart) {
        return new CartDto(cart.getId(),cart.getItems(),cart.isClosed(),orderMapper.translateToOrderDto(cart.getOrder()));
    }

    public UserOrder translateToOrder(Cart cart){
        return new UserOrder(cart.getId(), LocalDate.now(),false,cart.getOrder().getUser(),cart);
    }
}
