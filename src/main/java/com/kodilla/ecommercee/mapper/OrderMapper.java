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
    private CartMapper cartMapper;

    @Autowired
    private UserMapper userMapper;

    public UserOrder mapToOrder(OrderDto orderDto) {
        return new UserOrder(orderDto.getId(),orderDto.getOrderMade(),orderDto.isResolved(),orderDto.isMailSend(),userMapper.toUser(orderDto.getUser()),cartMapper.mapToCart(orderDto.getCartDto()));
    }

    public OrderDto mapToOrderDto(UserOrder order) {
        return new OrderDto(order.getId(),order.getOrderMade(),order.isResolved(),order.isMailSend(),userMapper.toUserDto(order.getUser()),cartMapper.mapToCartDto(order.getCart()));
    }

    public List<OrderDto> mapToOrderList(List<UserOrder> orders){
      return  orders.stream().map(this::mapToOrderDto)
               .collect(Collectors.toList());
    }
}
