package com.kodilla.ecommercee;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    private Order order;
    private OrderDto orderDto;

    public Order translateToOrder(OrderDto orderDto) {
        return new Order(orderDto.getId());
    }

    public OrderDto translateToOrderDto(Order order) {
        return new OrderDto(order.getId());
    }

    public List<OrderDto> translateToOrderList(List<Order> orders){
      return  orders.stream().map(o -> new OrderDto(o.getId()))
               .collect(Collectors.toList());
    }
}
