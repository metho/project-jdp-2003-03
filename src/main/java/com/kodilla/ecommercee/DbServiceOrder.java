package com.kodilla.ecommercee;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class DbServiceOrder {
    @NonNull
    private OrderMock orderMock;
    private OrderRepository orderRepository;

    public List<Order> getOrdersMock() {
        return orderMock.findAll();
    }

    public Order saveOrderMock(Order order) {
        return orderMock.save(order);
    }

    public Optional<Order> getOrderMock(Long orderId) {
        return orderMock.findById(orderId);
    }

    public void deleteOrderMock(Long orderId) {
        orderMock.deleteById(orderId);
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public Optional<Order> getOrder(Long orderId) {
        return orderRepository.findById(orderId);
    }

    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }
}
