package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.entity.UserOrder;
import com.kodilla.ecommercee.exception.EntityNotFoundException;
import com.kodilla.ecommercee.exception.ExceptionType;
import com.kodilla.ecommercee.exception.ForbiddenException;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;

    public List<UserOrder> getOrders() {
        return orderRepository.findAll();
    }

    public UserOrder saveOrder(UserOrder order) {
        getOrder(order.getId());
        return orderRepository.save(order);
    }

    public UserOrder getOrder(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(()->
                new EntityNotFoundException(ExceptionType.ORDER_NOT_FOUND, orderId.toString()));
    }

    public void deleteOrder(Long orderId) {
        UserOrder order = getOrder(orderId);

        if (!order.isResolved()) {
                orderRepository.deleteById(orderId);
                cartRepository.deleteById(order.getCart().getId());
        } else {
            throw new ForbiddenException(ExceptionType.ORDER_RESOLVED, orderId.toString());
        }
    }
}
