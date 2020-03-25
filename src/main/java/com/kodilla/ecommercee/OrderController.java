package com.kodilla.ecommercee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/v1/order")
public class OrderController {


    @Autowired
    private DbServiceOrder dbService;

    @Autowired
    private OrderMapper orderMapper;

    @RequestMapping(method = RequestMethod.GET, value = "getOrders")
    public List<OrderDto> getOrders(){
        return orderMapper.translateToOrderList(dbService.getOrders());
    }
    @RequestMapping(method = RequestMethod.POST, value = "createOrder")
    public void createOrder(OrderDto orderDto){
        dbService.saveOrder(orderMapper.translateToOrder(orderDto));
    }
    @RequestMapping(method = RequestMethod.GET, value = "getOrder")
    public OrderDto getOrder(Long orderId) throws OrderNotFoundException {
        return orderMapper.translateToOrderDto(dbService.getOrder(orderId).orElseThrow(OrderNotFoundException::new));
    }
    @RequestMapping(method = RequestMethod.PUT, value = "updateOrder")
    public OrderDto updateOrder(Order order){
        return orderMapper.translateToOrderDto(dbService.saveOrder(order));
    }
    @RequestMapping(method = RequestMethod.DELETE, value = "deleteOrder")
    public void deleteOrder(Long orderId){
        dbService.deleteOrder(orderId);
    }



}
