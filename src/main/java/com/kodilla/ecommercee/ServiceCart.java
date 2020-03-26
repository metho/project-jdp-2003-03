package com.kodilla.ecommercee;


public class ServiceCart {

    private CartRepository cartRepository;


    public Cart createCart(Cart cart){
        return new Cart();
    }

    public GenericEntity getProductFromCart(Cart cart) {
        return new GenericEntity();
    }

    public Cart addProductToCart(GenericEntity product){
        return new Cart();
    }

    public void deleteProductFromCart(GenericEntity product){
    }

    public ProductOrder createAnOrder(Cart cart){
        return new ProductOrder();
    }
}
