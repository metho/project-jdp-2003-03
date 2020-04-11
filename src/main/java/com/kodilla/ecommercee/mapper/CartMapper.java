package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.dto.CartDto;
import com.kodilla.ecommercee.dto.ItemDto;
import com.kodilla.ecommercee.entity.Cart;
import com.kodilla.ecommercee.entity.Item;
import com.kodilla.ecommercee.entity.Product;
import com.kodilla.ecommercee.exception.EntityNotFoundException;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class CartMapper {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;


    public Cart mapToCart(CartDto cartDto) {
        return new Cart(cartDto.getId(), mapToItemList(cartDto.getItems()), cartDto.isClosed());
    }

    public CartDto mapToCartDto(Cart cart) {
        return new CartDto(cart.getId(), mapToItemDtoList(cart.getItems()), cart.isClosed());
    }

    public ItemDto mapToItemDto(Item item){
        return new ItemDto(item.getId(), item.getCart().getId(), item.getProduct().getId(), item.getQuantity(), item.getPrice());
    }

    public Item mapToItem(ItemDto itemDto){
        Cart cart = cartRepository.findById(itemDto.getCartId()).orElseThrow(()->
                new EntityNotFoundException("Item with id "+ itemDto.getCartId() + " was not found."));
        Product product = productRepository.findById(itemDto.getProductId()).orElseThrow(()->
                new EntityNotFoundException("Product with id "+ itemDto.getProductId() + " was not found."));
        return new Item(itemDto.getId(), cart, product, itemDto.getQuantity(), itemDto.getPrice());
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
