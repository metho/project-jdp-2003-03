package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.OrderDto;
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
    public List<OrderDto> getOrders() {
        return orderMapper.mapToOrderList(service.getOrders());
    }


    @GetMapping("/{orderId}")
    public OrderDto getOrder(@PathVariable Long orderId) {
        return orderMapper.mapToOrderDto(service.getOrder(orderId));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public OrderDto updateOrder(@RequestBody OrderDto orderDto) {
        return orderMapper.mapToOrderDto(service.saveOrder(service.getOrder(orderDto.getId())));
    }

    @DeleteMapping("/{orderId}")
    public void deleteOrder(@PathVariable Long orderId){
        service.deleteOrder(orderId);
    }
}
