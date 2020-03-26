package com.kodilla.ecommercee;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/cart")
public class CartController {

    @PostMapping
    public Cart createCart(Cart cart){
        return new Cart();
    }
    @GetMapping
    public GenericEntity getProductFromCart(Cart cart){
        return new GenericEntity();
    }

    @PutMapping
    public Cart addProductToCart(GenericEntity product){
        return new Cart();
    }

    @DeleteMapping
    public void deleteProductFromCart(Long id){}

    @PostMapping
    public ProductOrder createAnOrder(Cart cart){
        return new ProductOrder();
    }
}
