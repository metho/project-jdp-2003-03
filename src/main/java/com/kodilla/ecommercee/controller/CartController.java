package com.kodilla.ecommercee.controller;
import com.kodilla.ecommercee.entity.Cart;
import com.kodilla.ecommercee.GenericEntity;
import com.kodilla.ecommercee.entity.UserOrder;



public class CartController {




    public Cart createCart(Cart cart){
        return new Cart();
    }

    public GenericEntity getProductFromCart(Cart cart){
        return new GenericEntity();
    }


    public Cart addProductToCart(GenericEntity product){
        return new Cart();
    }


    public void deleteProductFromCart(Long id){}


    public UserOrder createAnOrder(Cart cart){
        return new UserOrder();
    }

}
