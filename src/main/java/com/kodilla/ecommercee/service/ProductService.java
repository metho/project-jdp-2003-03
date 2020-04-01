package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.entity.Product;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> productList () {
        return productRepository.findAll();
    }

    public Optional<Product> getProduct(Long productId) {
        return productRepository.findById(productId);
    }

    public Product saveProduct(final Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long productId) {
         productRepository.deleteById(productId);
    }

}
