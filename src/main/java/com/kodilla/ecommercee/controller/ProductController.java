package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.ProductDto;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/product")
public class ProductController {

    private final ProductMapper productMapper;
    private final ProductService productService;

    @GetMapping
    public List<ProductDto> getProducts() {
        return productMapper.mapToProductDtoList(productService.productList());
    }

    @RequestMapping(method = RequestMethod.GET,  value = "/{productId}")
    public ProductDto getProduct(@PathVariable Long productId) {
        return productMapper.mapToProductDto(productService.getProduct(productId));
    }

    @GetMapping(value = "/{productName}")
    public ProductDto getProduct(@PathVariable String productName) {
        return productMapper.mapToProductDto(productService.getProduct(productName));
    }

    @PostMapping
    public void createProduct(ProductDto productDto) {
        productService.saveProduct(productMapper.mapToProduct(productDto));
    }

    @PutMapping
    public ProductDto updateProduct(ProductDto productDto) {
        return productMapper.mapToProductDto(productService.updateProduct(productMapper.mapToProduct(productDto)));
    }

    @DeleteMapping
    public void deleteProduct(Long productId) {
        productService.deleteProduct(productId);
    }
}
