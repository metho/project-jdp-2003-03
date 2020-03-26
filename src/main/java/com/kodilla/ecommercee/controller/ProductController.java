package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.ProductDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/products")
public class ProductController {

    @GetMapping
    public List<ProductDto> getProducts() {
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{productId}")
    public ProductDto getProduct(@PathVariable Long productId) {
        return new ProductDto();
    }

    @PostMapping
    public void createProduct(ProductDto productDto) {

    }

    @PutMapping
    public ProductDto updateProduct(Long productId) {
        return new ProductDto();
    }

    @DeleteMapping
    public void deleteProduct(Long productId) {

    }
}
