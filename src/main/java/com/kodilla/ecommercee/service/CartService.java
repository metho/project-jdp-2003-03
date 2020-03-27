package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.GenericEntity;
import com.kodilla.ecommercee.entity.UserOrder;
import com.kodilla.ecommercee.entity.Cart;
import com.kodilla.ecommercee.repository.CartRepository;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    private CartRepository cartRepository;


    public Cart createCart(Cart cart) {
        return new Cart();
    }

    public GenericEntity getProductFromCart(Cart cart) {
        return new GenericEntity();
    }

    public Cart addProductToCart(GenericEntity product) {
        return new Cart();
    }

    public void deleteProductFromCart(GenericEntity product) {}

    public UserOrder createAnOrder(Cart cart) {
        return new UserOrder();
    }
}
