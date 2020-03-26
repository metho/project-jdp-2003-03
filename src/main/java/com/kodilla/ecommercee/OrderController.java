package com.kodilla.ecommercee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/v1/order")
public class OrderController {


    @Autowired
    private ServiceOrder service;

    @Autowired
    private OrderMapper orderMapper;

    @GetMapping(value = "getOrders")
    public List<OrderDto> getOrders(){
        return orderMapper.translateToOrderList(service.getOrders());
    }
    @PostMapping(value = "createOrder")
    public void createOrder(OrderDto orderDto){
        service.saveOrder(orderMapper.translateToOrder(orderDto));
    }
    @GetMapping(value = "/{orderId}")
    public OrderDto getOrder(@PathVariable Long orderId) throws OrderNotFoundException {
        return orderMapper.translateToOrderDto(service.getOrder(orderId).orElseThrow(OrderNotFoundException::new));
    }
    @PutMapping(value = "updateOrder")
    public OrderDto updateOrder(ProductOrder order){
        return orderMapper.translateToOrderDto(service.saveOrder(order));
    }
    @DeleteMapping(value = "deleteOrder")
    public void deleteOrder(Long orderId){
        service.deleteOrder(orderId);
    }



}
