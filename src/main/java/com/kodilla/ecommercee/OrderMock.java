package com.kodilla.ecommercee;

import java.util.List;
import java.util.Optional;

public interface OrderMock {


    List<Order> findAll();


    Order save(Order order);


    Optional<Order> findById(Long orderId);


    void deleteById(Long orderId);
}
