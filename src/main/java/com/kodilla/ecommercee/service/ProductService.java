package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.entity.Product;
import com.kodilla.ecommercee.exception.EntityNotFoundException;
import com.kodilla.ecommercee.exception.ExceptionType;
import com.kodilla.ecommercee.repository.CartRepository;
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

    public Product getProduct(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionType.PRODUCT_NOT_FOUND, id.toString()));
    }

    public List<Product> productList () {
        return productRepository.findAll();
    }

    public List<Product> getProduct(String name) throws EntityNotFoundException {
        List<Product> productList = productRepository.findByName(name);
        if(productList.size() == 0) {
            throw new EntityNotFoundException(ExceptionType.PRODUCT_NOT_FOUND, name);
        }
        return productList;
    }

    public Product updateProduct(final Product productToUpdate) {
        if(productRepository.findById(productToUpdate.getId()).isPresent()) {
            productRepository.save(productToUpdate);
        } else {
            throw new EntityNotFoundException(ExceptionType.PRODUCT_NOT_FOUND, productToUpdate.toString());
        }
        return productToUpdate;
    }

    public void saveProduct(final Product product) {
        if(productRepository.existsByNameAndBrandAndModelAndYear(product.getName(), product.getBrand(), product.getModel(), product.getYear())
                .equals(product)) {
            throw new EntityNotFoundException(ExceptionType.PRODUCT_ALREADY_EXIST, product.getName());
        } else {
            productRepository.save(product);
        }
    }

    public void deleteProduct(Long productId) {
        Long countOfProducts = cartRepository.findAll().stream()
                .flatMap(cart -> cart.getItems().stream())
                .map(item1 -> item1.getProduct().getId() == productId)
                .count();

        if(countOfProducts != 0) {
            throw new EntityNotFoundException(ExceptionType.PRODUCT_BUSY, productId.toString());
        } else {
            productRepository.deleteById(productId);
        }
    }
}
