package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.dto.OrderDto;
import com.kodilla.ecommercee.entity.Cart;
import com.kodilla.ecommercee.entity.Item;
import com.kodilla.ecommercee.entity.User;
import com.kodilla.ecommercee.entity.UserOrder;
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

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    public Cart createCart() {
        return cartRepository.save(new Cart());
    }

    public Cart saveCart(Cart cart) {
        if (!cartRepository.existsById(cart.getId())) {
            throw new EntityNotFoundException("Cart with id " + cart.getId() + " was not found");
        } else {
            return cartRepository.save(cart);
        }
    }

    public Cart getCart(Long cartId) {
        return cartRepository.findById(cartId).orElseThrow(() ->
                new EntityNotFoundException("Cart with id " + cartId + " was not found."));
    }

    public void deleteCart(Long cartId) {
        cartRepository.findById(cartId).orElseThrow(() ->
                new EntityNotFoundException("Cart with id " + cartId + " was not found."));
        cartRepository.deleteById(cartId);
    }

    public Item getItem(Long itemId) {
        return itemRepository.findById(itemId).orElseThrow(()->
                new EntityNotFoundException("Item with id "+ itemId + " was not found."));
    }

    public Item saveItem(Item item){
        if (!itemRepository.existsById(item.getId())) {
            throw new EntityNotFoundException("Item with id " +item.getId()+ " was not found");
        } else {
            addItem(item);
            return item;
        }
    }

    public void addItem(Item item) {
        item.setPrice(item.getQuantity() * item.getProduct().getPrice());
        itemRepository.save(item);
    }

    public void deleteItem(Long itemId) {
        if (!itemRepository.existsById(itemId)) {
            throw new EntityNotFoundException("Item with id " + itemId + " was not found");
        }
        itemRepository.deleteById(itemId);
    }

    public UserOrder createAnOrder(OrderDto orderDto) {
        User user = userRepository.findById(orderDto.getUserId()).orElseThrow(()->
                        new EntityNotFoundException("User with id "+ orderDto.getUserId() + " was not found."));
        Cart cart = cartRepository.findById(orderDto.getCartId()).orElseThrow(()->
                new EntityNotFoundException("Cart with id "+ orderDto.getCartId() + " was not found."));
        cart.setClosed(true);
        return orderRepository.save(new UserOrder(user,cart));
    }

}
