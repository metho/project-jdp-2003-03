package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.entity.Product;
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

    public Product getProductFromCart(Cart cart) {
        return new Product();
    }

    public Cart addProductToCart(Product product) {
        return new Cart();
    }

    public void deleteProductFromCart(Product product) {}

    public UserOrder createAnOrder(Cart cart) {
        return new UserOrder();
    }
}
