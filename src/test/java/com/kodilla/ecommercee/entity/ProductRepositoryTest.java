package com.kodilla.ecommercee.entity;


import com.kodilla.ecommercee.repository.ProductGroupRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

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
        Product productOne = new Product("AAA", 2.0, productGroup);
        Product productTwo = new Product("BBB", 3.45, productGroup);
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
        Product productOne = new Product("AAA", 2.0, productGroup);
        Product productTwo = new Product("BBB", 3.45, productGroup);
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
        Product productOne = new Product("AAA", 2.0, productGroup);
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
        Product productOne = new Product("AAA", 3.0, productGroup);
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

    @Test
    public void testUpdateGroupInProduct() {
        // Given
        ProductGroup groupA = new ProductGroup("GroupA");
        ProductGroup groupB = new ProductGroup("GroupB");
        Product productA = new Product("AAA", 2.0, groupA);
        Product productB = new Product("BBB", 3.0, groupA);

        // When
        productGroupRepository.save(groupA);
        productGroupRepository.save(groupB);
        productRepository.save(productA);
        productRepository.save(productB);

        Optional<Product> oldProductA = productRepository.findById(productA.getId());
        Optional<Product> oldProductB = productRepository.findById(productB.getId());
        productRepository.updateGroupId(groupB, groupA);
        Optional<Product> newProductA = productRepository.findById(productA.getId());
        Optional<Product> newProductB = productRepository.findById(productB.getId());

        // Then
        assertTrue(oldProductA.isPresent());
        assertEquals(groupA, oldProductA.get().getProductGroup());
        assertTrue(oldProductB.isPresent());
        assertEquals(groupA, oldProductB.get().getProductGroup());
        assertTrue(newProductA.isPresent());
        assertEquals(groupB, newProductA.get().getProductGroup());
        assertTrue(newProductB.isPresent());
        assertEquals(groupB, newProductB.get().getProductGroup());

        // Clean
        productRepository.deleteById(productA.getId());
        productRepository.deleteById(productB.getId());
        productGroupRepository.deleteById(groupA.getId());
        productGroupRepository.deleteById(groupB.getId());
    }
}