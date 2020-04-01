package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.ProductDto;
import com.kodilla.ecommercee.entity.Product;
import com.kodilla.ecommercee.exception.ProductNotFoundException;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/products")
public class ProductController {

    @Autowired
    ProductMapper productMapper;
    @Autowired
    ProductService productService;

    @GetMapping
    public List<ProductDto> getProducts() {
        return productMapper.mapToProductDtoList(productService.productList());
    }

    @RequestMapping(method = RequestMethod.GET,  value = "/{productId}")
    public ProductDto getProduct(@PathVariable Long productId) throws ProductNotFoundException {
        return productMapper.mapToProductDto(productService.getProduct(productId).orElseThrow(ProductNotFoundException::new));
    }

    @PostMapping
    public void createProduct(ProductDto productDto) {
        productService.saveProduct(productMapper.mapToProduct(productDto));
    }

    @PutMapping
    public ProductDto updateProduct(ProductDto productDto) {
        return productMapper.mapToProductDto(productService.saveProduct(productMapper.mapToProduct(productDto)));
    }

    @DeleteMapping
    public void deleteProduct(Long productId) throws ProductNotFoundException {
        productService.deleteProduct(productId);

    }
}
