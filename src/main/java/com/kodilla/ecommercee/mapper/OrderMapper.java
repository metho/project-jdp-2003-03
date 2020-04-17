package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.dto.OrderDto;
import com.kodilla.ecommercee.entity.User;
import com.kodilla.ecommercee.entity.UserOrder;
import com.kodilla.ecommercee.exception.EntityNotFoundException;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    public UserOrder mapToOrder(OrderDto orderDto) {
        User user = userRepository.findById(orderDto.getUserId()).orElseThrow(()->
                new EntityNotFoundException("Order with id "+ orderDto.getUserId() + " was not found."));
        return new UserOrder(orderDto.getId(),orderDto.getOrderMade(),orderDto.isResolved(),
                orderDto.isMailSend(), userRepository.getOne(orderDto.getUserId()), cartRepository.getOne(orderDto.getCartId()) );
    }

    public OrderDto mapToOrderDto(UserOrder order) {
        return new OrderDto(order.getId(),order.getOrderMade(),order.isResolved(),
                order.isMailSent(),order.getUser().getId(),order.getCart().getId());
    }

    public List<OrderDto> mapToOrderList(List<UserOrder> orders) {
        return orders.stream().map(this::mapToOrderDto)
                .collect(Collectors.toList());
    }
}
