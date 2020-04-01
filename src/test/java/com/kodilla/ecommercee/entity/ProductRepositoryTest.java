package com.kodilla.ecommercee.entity;


import com.kodilla.ecommercee.repository.ProductGroupRepository;
import com.kodilla.ecommercee.repository.ProductRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductGroupRepository productGroupRepository;


    @Test
    public void shouldCreate(){
        //Given
        ProductGroup productGroup = new ProductGroup("Laptops");
        Product productOne = new Product(productGroup);
        Product productTwo = new Product(productGroup);
        productGroupRepository.save(productGroup);
        productRepository.save(productOne);
        productRepository.save(productTwo);


        //When
        Long size = productRepository.count();

        //Than
        assertTrue(size == 2);
    }

    @Test
    public void shouldRetrive(){

       //Given
        ProductGroup productGroup = new ProductGroup("Laptops");
        Product productOne = new Product(productGroup);
        Product productTwo = new Product(productGroup);
        productGroupRepository.save(productGroup);
        productRepository.save(productOne);
        productRepository.save(productTwo);
        Long productOneId = productOne.getId();


        //When

        String outcome = productRepository.findById(productOneId).get().getProductGroup().getName();

        //Than
        assertEquals("Laptops",outcome);

    }

    @Test
    public void shouldUpdate(){

        //Given
        ProductGroup productGroup = new ProductGroup("Laptops");
        ProductGroup productGroupTwo = new ProductGroup("Clothes");
        Product productOne = new Product(productGroup);
        productGroupRepository.save(productGroupTwo);
        productGroupRepository.save(productGroup);
        productRepository.save(productOne);
        Long productOneId = productOne.getId();


        //When
        Product productChange =productRepository.findById(productOneId).get();
        productChange.setProductGroup(productGroupTwo);
        productRepository.save(productChange);
        String outcome = productRepository.findById(productOneId).get().getProductGroup().getName();

        //Than
        assertEquals("Clothes",outcome);

    }

    @Test
    public void shouldDelete(){
        //Given
        ProductGroup productGroup = new ProductGroup("Laptops");
        Product productOne = new Product(productGroup);
        productGroupRepository.save(productGroup);
        productRepository.save(productOne);
        Long productOneId = productOne.getId();

        //When
        Long sizeBefore = productRepository.count();
        productRepository.deleteById(productOneId);
        Long sizeAfter = productRepository.count();

        //Than
        assertTrue(sizeBefore - sizeAfter == 1);

    }

}