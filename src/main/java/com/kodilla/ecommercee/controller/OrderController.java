package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.OrderDto;
import com.kodilla.ecommercee.exception.OrderNotFoundException;
import com.kodilla.ecommercee.exception.OrderAlreadyResolved;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/v1/order")
public class OrderController {

    @Autowired
    private OrderService service;

    @Autowired
    private OrderMapper orderMapper;

    @GetMapping
    public List<OrderDto> getOrders(){
        return orderMapper.mapToOrderList(service.getOrders());
    }


    @GetMapping("/{orderId}")
    public OrderDto getOrder(@PathVariable Long orderId) throws OrderNotFoundException {
        return orderMapper.mapToOrderDto(service.getOrder(orderId));
    }

    @PutMapping("/{orderId}")
    public OrderDto updateOrder(@PathVariable Long orderId) throws OrderNotFoundException {
        return orderMapper.mapToOrderDto(service.saveOrder(service.getOrder(orderId)));
    }

    @DeleteMapping("/{orderId}")
    public void deleteOrder(@PathVariable Long orderId) throws OrderAlreadyResolved {
        service.deleteOrder(orderId);
    }
}
