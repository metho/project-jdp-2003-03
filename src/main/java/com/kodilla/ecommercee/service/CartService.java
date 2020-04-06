package com.kodilla.ecommercee.service;


import com.kodilla.ecommercee.dto.OrderDto;
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




    public Cart createCart() {
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

    public UserOrder createAnOrder(OrderDto orderDto) throws CartNotFoundException, UserNotFoundException {
        if(cartRepository.getOne(orderDto.getCartDto().getId()) == null ){
            throw new CartNotFoundException();
        } else if (userRepository.getOne(orderDto.getUser().getId()) == null) {
            throw new UserNotFoundException();
        } else {
            return orderRepository.save(new UserOrder(userRepository.getOne(orderDto.getUser().getId()), cartRepository.getOne(orderDto.getCartDto().getId())));
        }
    }
}
