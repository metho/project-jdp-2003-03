package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.dto.ProductDto;
import com.kodilla.ecommercee.entity.Product;
import com.kodilla.ecommercee.exception.EntityNotFoundException;
import com.kodilla.ecommercee.exception.ExceptionType;
import com.kodilla.ecommercee.repository.ProductGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    @Autowired
    private ProductGroupRepository productGroupRepository;

    public Product mapToProduct(final ProductDto productDto) {
        Product product = new Product(
                productDto.getId(),
                productDto.getName(),
                productDto.getPrice(),
                productDto.getBrand(),
                productDto.getModel(),
                productDto.getYear(),
                productDto.getOrigin(),
                productDto.getDescription());

                product.setProductGroup(productGroupRepository.findById(productDto.getId())
                        .orElseThrow(() -> new EntityNotFoundException(ExceptionType.GROUP_NOT_FOUND, productDto.getId().toString())));
        return product;
    }

    public ProductDto mapToProductDto(final Product product) {
        ProductDto productDto = new ProductDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getBrand(),
                product.getModel(),
                product.getYear(),
                product.getOrigin(),
                product.getDescription());
        productDto.setGroupId(product.getProductGroup().getId());
        return productDto;
    }

    public List<ProductDto> mapToProductDtoList(final List<Product> productList) {
        return productList.stream()
                .map(this::mapToProductDto)
                .collect(Collectors.toList());
    }
}
