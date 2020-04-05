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



@Service
public class CartService {

    private CartRepository cartRepository;

    private ItemRepository itemRepository;

    private OrderRepository orderRepository;

    private UserRepository userRepository;

    private CartMapper cartMapper;

    private OrderMapper orderMapper;



    public Cart createCart(Cart cart) {
        return cartRepository.save(new Cart());
    }

    public Item getItem(Long itemId) {
        return itemRepository.getOne(itemId);
    }

    public boolean addItem(Item item) {
       return cartRepository.getOne(item.getCart().getId()).getItems().add(itemRepository.save(item));
    }

    public void deleteItem(Long itemId) {
        itemRepository.deleteById(itemId);
    }

    public UserOrder createAnOrder(UserOrder order) throws CartNotFoundException, UserNotFoundException {
        if(cartRepository.getOne(order.getCart().getId()) == null ){
            throw new CartNotFoundException();
        } else if (userRepository.getOne(order.getUser().getId()) == null) {
            throw new UserNotFoundException();
        } else {
            return orderRepository.save(new UserOrder(userRepository.getOne(order.getUser().getId()), cartRepository.getOne(order.getCart().getId())));
        }
    }
}
