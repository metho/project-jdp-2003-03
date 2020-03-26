package com.kodilla.ecommercee;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    public ProductOrder translateToOrder(OrderDto orderDto) {
        return new ProductOrder(orderDto.getId());
    }

    public OrderDto translateToOrderDto(ProductOrder order) {
        return new OrderDto(order.getId());
    }

    public List<OrderDto> translateToOrderList(List<ProductOrder> orders){
      return  orders.stream().map(o -> translateToOrderDto(o))
               .collect(Collectors.toList());
    }
}
