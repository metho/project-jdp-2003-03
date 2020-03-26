package com.kodilla.ecommercee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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
