package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.dto.OrderDto;
import com.kodilla.ecommercee.entity.Cart;
import com.kodilla.ecommercee.entity.Item;
import com.kodilla.ecommercee.entity.User;
import com.kodilla.ecommercee.entity.UserOrder;
import com.kodilla.ecommercee.exception.EntityNotFoundException;
import com.kodilla.ecommercee.exception.ExceptionType;
import com.kodilla.ecommercee.exception.ForbiddenException;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.ItemRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
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
    private UserService userService;

    public Cart createCart() {
        return cartRepository.save(new Cart());
    }

    public Cart saveCart(Cart cart) {
        getCart(cart.getId());
        return cartRepository.save(cart);
    }

    public Cart getCart(Long cartId) {
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionType.CART_NOT_FOUND, cartId.toString()));
    }

    public void deleteCart(Long cartId) {
        getCart(cartId);
        cartRepository.deleteById(cartId);
    }

    public Item getItem(Long itemId) {
        return itemRepository.findById(itemId).orElseThrow(()->
                new EntityNotFoundException(ExceptionType.ITEM_NOT_FOUND, itemId.toString()));
    }

    public Item saveItem(Item item){
        getItem(item.getId());
        return addItem(item);
    }

    public Item addItem(Item item) {
        if (cartRepository.existsByIdAndClosed(item.getCart().getId(), true)) {
            throw new ForbiddenException(ExceptionType.CART_IS_CLOSED, item.getCart().getId().toString());
        }
        item.setPrice(item.getQuantity() * item.getProduct().getPrice());
        return itemRepository.save(item);
    }

    public void deleteItem(Long itemId) {
        getItem(itemId);
        itemRepository.deleteById(itemId);
    }

    public UserOrder createAnOrder(OrderDto orderDto) {
        User user = userService.getUser(orderDto.getUserId());
        Cart cart = getCart(orderDto.getCartId());
        cart.setClosed(true);
        return orderRepository.save(new UserOrder(user, cart));
    }
}
