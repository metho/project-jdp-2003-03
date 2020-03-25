package com.kodilla.ecommercee;

import org.junit.Assert;
import org.junit.Test;


import java.util.ArrayList;

import static org.mockito.Mockito.*;

public class OrderControllerTestSuite {

    @Test
    public void shouldShowOrders(){
        //Given
        OrderMock mock = mock(OrderMock.class);
        Order orderOne = new Order(1l);
        Order orderTwo = new Order(2l);
        Order orderThree = new Order(3l);
        ArrayList<Order> orders = new ArrayList<>();
        orders.add(orderOne);
        orders.add(orderTwo);
        orders.add(orderThree);
        when(mock.findAll()).thenReturn(orders);
        DbServiceOrder dbServiceOrder = new DbServiceOrder(mock);

        //When
        dbServiceOrder.getOrdersMock();

        //Than
        Assert.assertEquals(3,orders.size());
    }
    @Test
    public void shouldShowChosenOrder(){
        OrderMock mock = mock(OrderMock.class);
        Order orderOne = new Order(1l);
        Order orderTwo = new Order(2l);
        Order orderThree = new Order(3l);
        ArrayList<Order> orders = new ArrayList<>();
        orders.add(orderOne);
        orders.add(orderTwo);
        orders.add(orderThree);
        when(mock.findById(1L)).thenReturn(java.util.Optional.ofNullable(orders.get(0)));
        DbServiceOrder dbServiceOrder = new DbServiceOrder(mock);

        //When
        dbServiceOrder.getOrderMock(1L);

        //Than
        Assert.assertTrue(orderOne.getId().equals(1L));
    }
    @Test
    public void shouldSaveOrder(){
        OrderMock mock = mock(OrderMock.class);
        Order orderOne = new Order(1l);
        ArrayList<Order> orders = new ArrayList<>();
        when(mock.save(any(Order.class))).thenReturn(orderOne);
        DbServiceOrder dbServiceOrder = new DbServiceOrder(mock);

        //When
        Order order = dbServiceOrder.saveOrderMock(orderOne);

        //Than
        Assert.assertTrue(order.getId()==1L);
    }
    @Test
    public void shouldDeleteOrder(){
        OrderMock mock = mock(OrderMock.class);
        Order orderOne = new Order(1l);
        Order orderTwo = new Order(2l);
        Order orderThree = new Order(3l);
        ArrayList<Order> orders = new ArrayList<>();
        orders.add(orderOne);
        orders.add(orderTwo);
        orders.add(orderThree);
        DbServiceOrder dbServiceOrder = new DbServiceOrder(mock);

        //When
        dbServiceOrder.deleteOrderMock(1L);

        //Then
        verify(mock,times(1)).deleteById(1L);


    }
}
