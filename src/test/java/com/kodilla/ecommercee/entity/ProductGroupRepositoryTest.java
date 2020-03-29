package com.kodilla.ecommercee.entity;

import com.kodilla.ecommercee.repository.ProductGroupRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductGroupRepositoryTest {

    @Autowired
    ProductGroupRepository repository;

    @Autowired
    ProductRepository productRepository;

    @Before
    public void printBefore() {
        System.out.println("\nStart test");
    }

    @Test
    public void testProductGroupGetAll() {
        // Given
        ProductGroup groupA = new ProductGroup("Photo camera");
        ProductGroup groupB = new ProductGroup("Computers");
        ProductGroup groupC = new ProductGroup("Potatoes");

        // When
        System.out.println("Test get all records ...\n");
        repository.save(groupA);
        repository.save(groupB);
        repository.save(groupC);

        List<ProductGroup> groups = repository.findAll();
        boolean found = groups.contains(groupB);

        // Then
        assertEquals(3, groups.size());
        assertTrue(found);

        repository.deleteAll();
    }

    @Test
    public void testProductGroupGetOne() {
        // Given
        String name = "Potatoes";
        ProductGroup groupA = new ProductGroup("Photo camera");
        ProductGroup groupB = new ProductGroup("Computers");
        ProductGroup groupC = new ProductGroup(name);

        // When
        System.out.println("Test get one record ...\n");
        repository.save(groupA);
        repository.save(groupB);
        repository.save(groupC);

        Long id = groupB.getId();
        Optional<ProductGroup> foundGroup = repository.findById(id);
        List<ProductGroup> foundGroupByName = repository.findByName(name);

        // Then
        assertTrue(foundGroup.isPresent());
        assertEquals(groupB, foundGroup.get());
        assertEquals(groupC, foundGroupByName.get(0));

        repository.deleteAll();
    }

    @Test
    public void testProductGroupUpdateDelete() {
        // Given
        String name = "Potatoes";
        String newName = "Vegetables";
        ProductGroup groupA = new ProductGroup("Photo camera");
        ProductGroup groupB = new ProductGroup("Computers");
        ProductGroup groupC = new ProductGroup(name);

        // When
        System.out.println("Test update record ...\n");
        repository.save(groupA);
        repository.save(groupB);
        repository.save(groupC);

        List<ProductGroup> foundOld = repository.findByName(name);

        groupC.setName(newName);
        repository.save(groupC);
        List<ProductGroup> foundNew = repository.findByName(newName);

        repository.deleteById(groupA.getId());
        List<ProductGroup> groups = repository.findAll();

        // Then
        assertEquals(name, foundOld.get(0).getName());
        assertEquals(groupC, foundNew.get(0));
        assertEquals(2, groups.size());

        repository.deleteAll();
    }

    @Test
    public void testProductAndProductGroup() {
        // Given
        ProductGroup group = new ProductGroup("Product");
        Product productA = new Product();
        Product productB = new Product();
        Product productC = new Product();
        productA.setProductGroup(group);
        productB.setProductGroup(group);
        productC.setProductGroup(group);
        group.getProducts().addAll(Arrays.asList(productA, productB, productC));

        // When
        repository.save(group);
        productRepository.save(productA);
        productRepository.save(productB);
        productRepository.save(productC);

        System.out.println("Test product and productGroup ...\n");
        repository.save(group);
        Long id = group.getId();
        Optional<ProductGroup> foundGroup = repository.findById(id);

        //Then
        assertTrue(foundGroup.isPresent());
        assertEquals(3, foundGroup.get().getProducts().size());

        productRepository.deleteAll();
        repository.deleteAll();
    }

    @Test
    public void testExistsProductGroup() {
        // Given
        String name = "Potatoes";
        ProductGroup groupA = new ProductGroup("Photo camera");
        ProductGroup groupB = new ProductGroup("Computers");
        ProductGroup groupC = new ProductGroup(name);

        // When
        System.out.println("Test exists records ...\n");
        repository.save(groupA);
        repository.save(groupB);
        repository.save(groupC);

        Long id = groupB.getId();
        boolean existsById = repository.existsById(id);
        boolean existsByName = repository.existsByName(name);

        // Then
        assertTrue(existsById);
        assertTrue(existsByName);

        repository.deleteAll();
    }
}
