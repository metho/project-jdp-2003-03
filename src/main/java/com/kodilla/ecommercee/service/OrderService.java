package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.entity.UserOrder;
import com.kodilla.ecommercee.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class OrderService {

    private OrderRepository orderRepository;

    public List<UserOrder> getOrders() {
        return orderRepository.findAll();
    }

    public UserOrder saveOrder(UserOrder order) {
        return orderRepository.save(order);
    }

    public Optional<UserOrder> getOrder(Long orderId) {
        return orderRepository.findById(orderId);
    }

    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }
}
