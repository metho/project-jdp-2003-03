package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.entity.Cart;
import com.kodilla.ecommercee.entity.Item;
import com.kodilla.ecommercee.entity.Product;
import com.kodilla.ecommercee.exception.EntityNotFoundException;
import com.kodilla.ecommercee.exception.ProductNotFoundException;
import com.kodilla.ecommercee.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

@Service
@RequiredArgsConstructor
public class ProductService {
    private Item item;
    private Cart cart;

    private ProductRepository productRepository;

    public List<Product> productList () {
        return productRepository.findAll();
    }

    public Product getProduct(Long productId) throws EntityNotFoundException {
        return productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("Product not found"));
    }

    public Product getProduct(String name) throws EntityNotFoundException {
        return productRepository.findByName(name).orElseThrow(() -> new EntityNotFoundException("Product not found"));
    }

    public Product saveProduct(final Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(final Product product) {
        if(productRepository.existsByNameAndBrandAndModelAndYear().equals(product)) {
            throw new EntityNotFoundException("This product already exist");
        } else {
            new Product();
        }
        return productRepository.save(product);
    }

    public void deleteProduct(Long productId) {
        Long prodId = cart.getItems().stream()
                .map(item1 -> item1.getProduct().getId() == productId)
                .count();

        if(prodId != null) {
            throw new EntityNotFoundException("Can't delete product, because is used in Item");
        } else {
            productRepository.deleteById(productId);
        }
    }
}
