package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.*;
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
        return orderMapper.translateToOrderList(service.getOrders());
    }
    @PostMapping
    public void createOrder(OrderDto orderDto){
        service.saveOrder(orderMapper.translateToOrder(orderDto));
    }
    @GetMapping(value = "/{orderId}")
    public OrderDto getOrder(@PathVariable Long orderId) throws OrderNotFoundException {
        return orderMapper.translateToOrderDto(service.getOrder(orderId).orElseThrow(OrderNotFoundException::new));
    }
    @PutMapping
    public OrderDto updateOrder(UserOrder order){
        return orderMapper.translateToOrderDto(service.saveOrder(order));
    }
    @DeleteMapping
    public void deleteOrder(Long orderId){
        service.deleteOrder(orderId);
    }

}
