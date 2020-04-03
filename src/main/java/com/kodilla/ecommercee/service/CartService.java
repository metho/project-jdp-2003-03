package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.dto.CartDto;
import com.kodilla.ecommercee.entity.Item;
import com.kodilla.ecommercee.entity.Product;
import com.kodilla.ecommercee.entity.UserOrder;
import com.kodilla.ecommercee.entity.Cart;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.ItemRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import org.springframework.stereotype.Service;



@Service
public class CartService {

    private CartRepository cartRepository;

    private ItemRepository itemRepository;

    private OrderRepository orderRepository;

    private CartMapper cartMapper;



    public Cart createCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public Item getItem(Long itemId) {
        return itemRepository.getOne(itemId);
    }

    public boolean addItem(Long itemId, Long cartId) {
       return cartRepository.getOne(cartId).getItems().add(itemRepository.save(itemRepository.getOne(itemId)));
    }

    public void deleteItem(Long itemId, Long cartId) {
        cartRepository.getOne(cartId).getItems().remove(itemRepository.getOne(itemId));
    }

    public UserOrder createAnOrder(Cart cart) {
        return orderRepository.save(cartMapper.translateToOrder(cart));

    }
}
