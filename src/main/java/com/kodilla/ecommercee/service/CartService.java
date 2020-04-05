package com.kodilla.ecommercee.service;


import com.kodilla.ecommercee.entity.Item;
import com.kodilla.ecommercee.entity.UserOrder;
import com.kodilla.ecommercee.entity.Cart;
import com.kodilla.ecommercee.exception.CartNotFoundException;
import com.kodilla.ecommercee.exception.UserNotFoundException;
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

    public UserOrder createAnOrder(Cart cart) throws CartNotFoundException, UserNotFoundException {
        if(cartRepository.getOne(cart.getId()) == null ){
            throw new CartNotFoundException();
        } else if (userRepository.getOne(cart.getUser().getId()) == null) {
            throw new UserNotFoundException();
        } else {
            return orderRepository.save(new UserOrder(userRepository.getOne(cart.getUser().getId()), cartRepository.getOne(cart.getId())));
        }
    }
}
