package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.dto.ProductDto;
import com.kodilla.ecommercee.dto.group.ProductGroupDto;
import com.kodilla.ecommercee.entity.Product;
import com.kodilla.ecommercee.entity.ProductGroup;
import com.kodilla.ecommercee.exception.EntityNotFoundException;
import com.kodilla.ecommercee.exception.ExceptionType;
import com.kodilla.ecommercee.repository.ProductGroupRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductGroupRepository groupRepository;

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product getProduct(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionType.PRODUCT_NOT_FOUND, id.toString()));
    }

    public List<ProductDto> getProductsByGroup(String name) {
        ProductGroup group = groupRepository.findFirstByName(name).orElseThrow(() -> new EntityNotFoundException(ExceptionType.GROUP_NOT_FOUND, name));
        List<ProductDto> products = productRepository.findByProductGroup(group).stream()
                .map(i -> new ProductDto(i.getId(), i.getName(), i.getPrice(), i.getBrand(), i.getModel(), i.getYear(), i.getOrigin(), i.getDescription()))
                .collect(Collectors.toList());

        return products;
    }
}
