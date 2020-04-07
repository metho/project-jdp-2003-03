package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.dto.OrderDto;
import com.kodilla.ecommercee.entity.Item;
import com.kodilla.ecommercee.entity.UserOrder;
import com.kodilla.ecommercee.entity.Cart;
import com.kodilla.ecommercee.exception.EntityNotFoundException;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.ItemRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;





    public Cart createCart() {
        return cartRepository.save(new Cart());
    }

    public Cart saveCart(Cart cart) {
        if (!cartRepository.existsById(cart.getId())) {
            throw new EntityNotFoundException("Cart with id " +cart.getId()+ " was not found");
        } else {
            return cartRepository.save(cart);
        }
    }

    public Cart getCart(Long cartId) {
        return cartRepository.findById(cartId).orElseThrow(()->new EntityNotFoundException("Cart with id "+ cartId + " was not found."));
    }

    public void deleteCart(Long cartId) {
        cartRepository.findById(cartId).orElseThrow(()->new EntityNotFoundException("Cart with id "+ cartId + " was not found."));
        cartRepository.deleteById(cartId);
    }

}
