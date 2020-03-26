package com.kodilla.ecommercee;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    public UserOrder translateToOrder(OrderDto orderDto) {
        return new UserOrder(orderDto.getId());
    }

    public OrderDto translateToOrderDto(UserOrder order) {
        return new OrderDto(order.getId());
    }

    public List<OrderDto> translateToOrderList(List<UserOrder> orders){
      return  orders.stream().map(o -> translateToOrderDto(o))
               .collect(Collectors.toList());
    }
}
