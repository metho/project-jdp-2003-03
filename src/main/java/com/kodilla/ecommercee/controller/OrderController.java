package com.kodilla.ecommercee.controller;
import com.kodilla.ecommercee.dto.OrderDto;
import com.kodilla.ecommercee.exception.OrderNotFoundException;
import com.kodilla.ecommercee.exception.OrderNotResolved;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
        return orderMapper.translateToOrderList(service.getOrders());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createOrder(OrderDto orderDto){
        service.saveOrder(orderMapper.translateToOrder(orderDto));
    }

    @GetMapping(value = "/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public OrderDto getOrder(@PathVariable Long orderId) throws OrderNotFoundException {
        return orderMapper.translateToOrderDto(service.getOrder(orderId).orElseThrow(OrderNotFoundException::new));
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public OrderDto updateOrder(@PathVariable Long orderId) throws OrderNotFoundException {
        return orderMapper.translateToOrderDto(service.saveOrder(service.getOrder(orderId).orElseThrow(OrderNotFoundException::new)));

    }

    @DeleteMapping(value = "/{orderId}")
    public void deleteOrder(@PathVariable Long orderId) throws OrderNotResolved {
        service.deleteOrder(orderId);
    }
}
