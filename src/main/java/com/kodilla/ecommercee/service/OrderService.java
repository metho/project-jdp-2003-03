package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.entity.UserOrder;
import com.kodilla.ecommercee.exception.OrderAlreadyResolved;
import com.kodilla.ecommercee.exception.OrderNotFoundException;
import com.kodilla.ecommercee.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<UserOrder> getOrders() {
        return orderRepository.findAll();
    }

    public UserOrder saveOrder(UserOrder order) {
        return orderRepository.save(order);
    }

    public UserOrder getOrder(Long orderId) throws OrderNotFoundException {
        return orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
    }

    public void deleteOrder(Long orderId) throws OrderAlreadyResolved {
        if (orderRepository.existsById(orderId)) {
            if (!orderRepository.getOne(orderId).isResolved()) {
                orderRepository.deleteById(orderId);
            } else {
                throw new OrderAlreadyResolved();
            }
        }

    }
}
