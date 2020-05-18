package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.entity.Product;
import com.kodilla.ecommercee.exception.EntityNotFoundException;
import com.kodilla.ecommercee.exception.ExceptionType;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.ItemRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ItemRepository itemRepository;

    public List<Product> productList() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionType.PRODUCT_NOT_FOUND, id.toString()));
    }

    public Product getProductByName(String name) {
        return productRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionType.PRODUCT_NOT_FOUND, name));
    }

    public Product updateProduct(final Product product) {
        if (productRepository.findById(product.getId()).isPresent()) {
            productRepository.save(product);
        } else {
            throw new EntityNotFoundException(ExceptionType.PRODUCT_NOT_FOUND, product.toString());
        }
        return product;
    }

    public void saveProduct(final Product product) {
        if (productRepository.existsByNameAndBrandAndModelAndYear(product.getName(), product.getBrand(), product.getModel(), product.getYear())) {
            throw new EntityNotFoundException(ExceptionType.PRODUCT_ALREADY_EXIST, product.getName());
        } else {
            productRepository.save(product);
        }
    }

    public void deleteProduct(Long id) {
        if (itemRepository.existsByProductId(id)) {
            throw new EntityNotFoundException(ExceptionType.PRODUCT_BUSY, id.toString());
        } else {
            productRepository.deleteById(id);
        }
    }
}
