package com.kodilla.ecommercee.mapper;
import com.kodilla.ecommercee.entity.UserOrder;
import com.kodilla.ecommercee.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    @Autowired
    CartMapper cartMapper;

    @Autowired
    UserMapper userMapper;

    public UserOrder translateToOrder(OrderDto orderDto) {
        return new UserOrder(orderDto.getId(),orderDto.getOrderMade(),orderDto.isResolved(),userMapper.translateToUser(orderDto.getUser()),cartMapper.translateToCart(orderDto.getCartDto()));
    }

    public OrderDto translateToOrderDto(UserOrder order) {
        return new OrderDto();
    }

    public List<OrderDto> translateToOrderList(List<UserOrder> orders){
      return  orders.stream().map(this::translateToOrderDto)
               .collect(Collectors.toList());
    }
}
