package com.kodilla.ecommercee.entity;


import com.kodilla.ecommercee.repository.ProductGroupRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductGroupRepository productGroupRepository;


    @Test
    public void shouldCreate() {
        //Given
        ProductGroup productGroup = new ProductGroup("Laptops");
        Product productOne = new Product(1L,"AAA", 2.0, "brand", "model", 1999, "origin",
                "description", productGroup, new ArrayList<>());
        Product productTwo = new Product(2L,"BBB", 3.0, "brand", "model", 2000, "origin",
                "description", productGroup, new ArrayList<>());
        productGroupRepository.save(productGroup);
        productRepository.save(productOne);
        productRepository.save(productTwo);

        //When
        Long size = productRepository.count();

        //Than
        assertTrue(size == 2);

        //Clean
        productRepository.deleteById(productOne.getId());
        productRepository.deleteById(productTwo.getId());
        productGroupRepository.deleteById(productGroup.getId());
    }

    @Test
    public void shouldRetrive() {
        //Given
        ProductGroup productGroup = new ProductGroup("Laptops");
        Product productOne =new Product(1L,"AAA", 2.0, "brand", "model", 1999, "origin",
                "description", productGroup, new ArrayList<>());
        Product productTwo = new Product(2L,"BBB", 3.0, "brand", "model", 2000, "origin",
                "description", productGroup, new ArrayList<>());
        productGroupRepository.save(productGroup);
        productRepository.save(productOne);
        productRepository.save(productTwo);
        Long productOneId = productOne.getId();

        //When
        String outcome = productRepository.findById(productOneId).get().getProductGroup().getName();

        //Than
        assertEquals("Laptops", outcome);

        //Clean
        productRepository.deleteById(productOne.getId());
        productRepository.deleteById(productTwo.getId());
        productGroupRepository.deleteById(productGroup.getId());
    }

    @Test
    public void shouldUpdate(){
        //Given
        ProductGroup productGroup = new ProductGroup("Laptops");
        ProductGroup productGroupTwo = new ProductGroup("Clothes");
        Product productOne = new Product(1L,"AAA", 2.0, "brand", "model", 1999, "origin",
                "description", productGroup, new ArrayList<>());
        productGroupRepository.save(productGroupTwo);
        productGroupRepository.save(productGroup);
        productRepository.save(productOne);
        Long productOneId = productOne.getId();

        //When
        Product productChange = productRepository.findById(productOneId).get();
        productChange.setProductGroup(productGroupTwo);
        productRepository.save(productChange);
        String outcome = productRepository.findById(productOneId).get().getProductGroup().getName();

        //Than
        assertEquals("Clothes", outcome);

        //Clean
        productRepository.deleteById(productOne.getId());
        productGroupRepository.deleteById(productGroup.getId());
        productGroupRepository.deleteById(productGroupTwo.getId());
    }

    @Test
    public void shouldDelete(){
        //Given
        ProductGroup productGroup = new ProductGroup("Laptops");
        Product productOne = new Product(1L,"AAA", 2.0, "brand", "model", 1999, "origin",
                "description", productGroup, new ArrayList<>());
        productGroupRepository.save(productGroup);
        productRepository.save(productOne);
        Long productOneId = productOne.getId();

        //When
        long sizeBefore = productRepository.count();
        productRepository.deleteById(productOneId);
        long sizeAfter = productRepository.count();
        long differ = sizeBefore - sizeAfter;

        //Than
        assertEquals(1, differ);

        //Clean
        productGroupRepository.deleteById(productGroup.getId());
    }

}