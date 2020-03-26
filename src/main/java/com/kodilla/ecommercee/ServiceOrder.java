package com.kodilla.ecommercee;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ServiceOrder {

    private OrderRepository orderRepository;


    public List<ProductOrder> getOrders() {
        return orderRepository.findAll();
    }

    public ProductOrder saveOrder(ProductOrder order) {
        return orderRepository.save(order);
    }

    public Optional<ProductOrder> getOrder(Long orderId) {
        return orderRepository.findById(orderId);
    }

    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }
}
