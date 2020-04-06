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
        return cartRepository.save(cart);
    }

    public Cart getCart(Long cartId) {
        return cartRepository.findById(cartId).orElseThrow(()->new EntityNotFoundException("Cart with id "+ cartId + " was not found."));
    }

    public void deleteCart(Long cartId) {
        cartRepository.findById(cartId).orElseThrow(()->new EntityNotFoundException("Cart with id "+ cartId + " was not found."));
        cartRepository.deleteById(cartId);
    }

    public Item getItem(Long itemId) {
        return itemRepository.getOne(itemId);
    }

    public Cart updateCart(Cart cart){
        return saveCart(getCart(cart.getId()));
    }

    public boolean addItem(Item item) {
       return cartRepository.getOne(item.getCart().getId()).getItems().add(itemRepository.save(item));
    }

    public void deleteItem(Long itemId) {
        itemRepository.deleteById(itemId);
    }

    public UserOrder createAnOrder(OrderDto orderDto) {
        return orderRepository.save(new UserOrder(userRepository.findById(orderDto.getUser().getId()).orElseThrow(()->new EntityNotFoundException("User with id "+ orderDto.getUser().getId() + " was not found.")),
                cartRepository.findById(orderDto.getCartDto().getId()).orElseThrow(()->new EntityNotFoundException("Cart with id "+ orderDto.getCartDto().getId() + " was not found."))));
    }
}
