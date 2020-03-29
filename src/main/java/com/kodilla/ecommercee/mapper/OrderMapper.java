package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.entity.UserOrder;
import com.kodilla.ecommercee.dto.UserOrderDto;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    public UserOrder translateToOrder(UserOrderDto userOrderDto) {
        return new UserOrder(userOrderDto.getId(), userOrderDto.getOrderMade(), userOrderDto.isResolved());
    }

    public UserOrderDto translateToOrderDto(UserOrder order) {
        return new UserOrderDto(order.getId(),order.getOrderMade(),order.isResolved());
    }

    public List<UserOrderDto> translateToOrderList(List<UserOrder> orders){
      return  orders.stream().map(o -> translateToOrderDto(o))
               .collect(Collectors.toList());
    }

}
