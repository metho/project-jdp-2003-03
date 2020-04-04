package com.kodilla.ecommercee.service;


import com.kodilla.ecommercee.entity.Item;
import com.kodilla.ecommercee.entity.UserOrder;
import com.kodilla.ecommercee.entity.Cart;
import com.kodilla.ecommercee.exception.CartNotFoundException;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.ItemRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
public class CartService {

    private CartRepository cartRepository;

    private ItemRepository itemRepository;

    private OrderRepository orderRepository;

    private UserRepository userRepository;

    private CartMapper cartMapper;

    private OrderMapper orderMapper;



    public Cart createCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public Item getItem(Long itemId) {
        return itemRepository.getOne(itemId);
    }

    public boolean addItem(Item item, Long cartId) {
       return cartRepository.getOne(cartId).getItems().add(itemRepository.save(item));
    }

    public void deleteItem(Long itemId) {
        itemRepository.deleteById(itemId);
    }

    public UserOrder createAnOrder(Long cartId, Long userId) throws CartNotFoundException, UserNotFoundException {
        if(cartRepository.getOne(cartId) == null ){
            throw new CartNotFoundException();
        } else if (userRepository.getOne(userId) == null) {
            throw new UserNotFoundException();
        } else {
            return orderRepository.save(new UserOrder(cartId, LocalDate.now(), userRepository.getOne(userId), cartRepository.getOne(cartId)));
        }
    }
}
