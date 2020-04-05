package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.dto.CartDto;
import com.kodilla.ecommercee.dto.ItemDto;
import com.kodilla.ecommercee.dto.OrderDto;
import com.kodilla.ecommercee.entity.Cart;
import com.kodilla.ecommercee.entity.Item;
import com.kodilla.ecommercee.entity.UserOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class CartMapper {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    ProductMapper productMapper;



    public Cart mapToCart(CartDto cartDto) {
        return new Cart(cartDto.getId(),mapToItemList(cartDto.getItems()),cartDto.isClosed());
    }

    public CartDto mapToCartDto(Cart cart) {
        return new CartDto(cart.getId(),mapToItemDtoList(cart.getItems()),cart.isClosed());
    }

    public ItemDto mapToItemDto(Item item){
        return new ItemDto(mapToCartDto(item.getCart()),productMapper.mapToProductDto(item.getProduct()),item.getQuantity(),item.getPrice());
    }
    public Item mapToItem(ItemDto itemDto){
        return new Item(mapToCart(itemDto.getCartId()),productMapper.mapToProduct(itemDto.getProductId()),itemDto.getQuantity(),itemDto.getPrice());
    }

    public List<ItemDto> mapToItemDtoList(List<Item> items){
        return  items.stream().map(this::mapToItemDto)
                .collect(Collectors.toList());
    }

    public List<Item> mapToItemList(List<ItemDto> items){
        return  items.stream().map(this::mapToItem)
                .collect(Collectors.toList());
    }

}
