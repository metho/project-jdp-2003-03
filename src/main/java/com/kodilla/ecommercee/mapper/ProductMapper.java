package com.kodilla.ecommercee.mapper;


import com.kodilla.ecommercee.dto.ProductDto;
import com.kodilla.ecommercee.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {


    public Product mapToProduct(ProductDto productDto) {
        return new Product();
    }

    public ProductDto mapToProductDto(Product product) {
        return new ProductDto();
    }
}
