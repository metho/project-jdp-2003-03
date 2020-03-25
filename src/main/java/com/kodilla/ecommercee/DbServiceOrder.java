package com.kodilla.ecommercee;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class DbServiceOrder {

    private OrderMock orderRepository;

    public List<Order> getOrders(){
        return orderRepository.findAll();
    }

    public Order saveOrder(Order order){
        return orderRepository.save(order);
    }

    public Optional<Order> getOrder(Long orderId){
        return orderRepository.findById(orderId);
    }

    public void deleteOrder(Long orderId){
        orderRepository.deleteById(orderId);
    }
}
