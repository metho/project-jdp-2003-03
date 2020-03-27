package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.GenericEntity;
import com.kodilla.ecommercee.entity.UserOrder;
import com.kodilla.ecommercee.entity.Cart;
import com.kodilla.ecommercee.repository.CartRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CartService extends OrderService {

    private CartRepository cartRepository;

    private OrderService orderService;


    public Cart createCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public Optional<GenericEntity> getProductFromCart(Long cartId, int productId) {
        return Optional.ofNullable(cartRepository.getOne(cartId).getProducts().get(productId));
    }

    public boolean addProductToCart(Long cartId,GenericEntity product) {
        return cartRepository.getOne(cartId).getProducts().add(product);
    }

    public void deleteProductFromCart(Long cartId, int productId) {
        cartRepository.getOne(cartId).getProducts().remove(productId);
    }

    public Cart getCart(Long cartId){
        return cartRepository.getOne(cartId);
    }

    public UserOrder createAnOrderFromCart(UserOrder order) {
        return orderService.saveOrder(order);
    }
}
