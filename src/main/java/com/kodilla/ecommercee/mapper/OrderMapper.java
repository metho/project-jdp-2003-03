package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.dto.OrderDto;
import com.kodilla.ecommercee.entity.Cart;
import com.kodilla.ecommercee.entity.User;
import com.kodilla.ecommercee.entity.UserOrder;
import com.kodilla.ecommercee.service.CartService;
import com.kodilla.ecommercee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    public UserOrder mapToOrder(OrderDto orderDto) {
        User user = userService.getUser(orderDto.getUserId());
        Cart cart = cartService.getCart(orderDto.getCartId());
        return new UserOrder(orderDto.getId(), orderDto.getOrderMade(), orderDto.isResolved(), orderDto.isMailSend(), user, cart);
    }

    public OrderDto mapToOrderDto(UserOrder order) {
        return new OrderDto(order.getId(), order.getOrderMade(), order.isResolved(),
                order.isMailSent(), order.getUser().getId(), order.getCart().getId());
    }

    public List<OrderDto> mapToOrderList(List<UserOrder> orders) {
        return orders.stream().map(this::mapToOrderDto)
                .collect(Collectors.toList());
    }
}
