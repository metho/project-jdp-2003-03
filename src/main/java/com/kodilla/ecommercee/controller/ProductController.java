package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.ProductDto;
import com.kodilla.ecommercee.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    public List<ProductDto> getProducts() {
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.GET,  value = "/{productId}")
    public ProductDto getProduct(@PathVariable Long productId) {
        return null;
    }

    @PostMapping
    public void createProduct(ProductDto productDto) {
    }

    @PutMapping
    public ProductDto updateProduct(Long productId) {
        return null;
    }

    @DeleteMapping
    public void deleteProduct(Long productId) {

    }

    @GetMapping("/bygroup")
    public List<ProductDto> getProducts(@RequestParam("name") String name) {
        return service.getProductsByGroup(name);
    }
}
