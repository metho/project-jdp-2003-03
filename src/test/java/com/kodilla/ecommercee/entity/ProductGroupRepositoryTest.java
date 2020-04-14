package com.kodilla.ecommercee.entity;

import com.kodilla.ecommercee.repository.ProductGroupRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
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
        ProductGroup groupA = new ProductGroup("AAA");
        ProductGroup groupB = new ProductGroup("BBB");
        ProductGroup groupC = new ProductGroup("CCC");

        // When
        System.out.println("Test get all records ...\n");
        repository.save(groupA);
        repository.save(groupB);
        repository.save(groupC);

        List<ProductGroup> groups = repository.findAll();
        boolean found = groups.contains(groupB);

        // Then
        assertTrue(groups.size() >= 3);
        assertTrue(found);

        // Clean
        repository.deleteById(groupA.getId());
        repository.deleteById(groupB.getId());
        repository.deleteById(groupC.getId());
    }

    @Test
    public void testProductGroupGetOne() {
        // Given
        String name = "DDD";
        ProductGroup groupA = new ProductGroup("EEE");
        ProductGroup groupB = new ProductGroup("FFF");
        ProductGroup groupC = new ProductGroup(name);

        // When
        System.out.println("Test get one record ...\n");
        repository.save(groupA);
        repository.save(groupB);
        repository.save(groupC);

        Long id = groupB.getId();
        Optional<ProductGroup> foundGroup = repository.findById(id);
        Optional<ProductGroup> foundGroupByName = repository.findFirstByName(name);

        // Then
        assertTrue(foundGroup.isPresent());
        assertEquals(groupB, foundGroup.get());
        assertTrue(foundGroupByName.isPresent());
        assertEquals(groupC, foundGroupByName.get());

        // Clean
        repository.deleteById(groupA.getId());
        repository.deleteById(groupB.getId());
        repository.deleteById(groupC.getId());
    }

    @Test
    public void testProductGroupUpdateDelete() {
        // Given
        String name = "GGG";
        String newName = "HHH";
        ProductGroup groupA = new ProductGroup("III");
        ProductGroup groupB = new ProductGroup("JJJ");
        ProductGroup groupC = new ProductGroup(name);

        // When
        System.out.println("Test update record ...\n");
        repository.save(groupA);
        repository.save(groupB);
        repository.save(groupC);

        Optional<ProductGroup> foundOld = repository.findFirstByName(name);
        String foundOldName = foundOld.isPresent() ? foundOld.get().getName() : "none";

        groupC.setName(newName);
        repository.save(groupC);
        Optional<ProductGroup> foundNew = repository.findFirstByName(newName);

        repository.deleteById(groupA.getId());
        Optional<ProductGroup> deleted = repository.findById(groupA.getId());

        // Then
        assertFalse(deleted.isPresent());
        assertTrue(foundOld.isPresent());
        assertEquals(name, foundOldName);
        assertTrue(foundNew.isPresent());
        assertEquals(groupC, foundNew.get());

        // Clean
        repository.deleteById(groupB.getId());
        repository.deleteById(groupC.getId());
    }

    @Test
    public void testProductAndProductGroup() {
        // Given
        ProductGroup group = new ProductGroup("Product");
        Product productA = new Product("AAA", 2.0, group);
        Product productB = new Product("BBB", 3.0, group);
        Product productC = new Product("CCC", 4.0, group);
        productA.setName("product1");
        productB.setName("product2");
        productC.setName("product3");
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

        // Clean
        productRepository.deleteById(productA.getId());
        productRepository.deleteById(productB.getId());
        productRepository.deleteById(productC.getId());
        repository.deleteById(group.getId());
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

        // Clean
        repository.deleteById(groupA.getId());
        repository.deleteById(groupB.getId());
        repository.deleteById(groupC.getId());
    }

    @Test
    public void testFindFirstByName() {
        // Given
        String name = "Same";
        String empty = "Empty";
        String tmp = "Temporary";
        ProductGroup groupA = new ProductGroup(name);
        ProductGroup groupB = new ProductGroup(name);
        ProductGroup groupC = new ProductGroup(name);

        // When
        System.out.println("Test find first records ...\n");
        repository.save(groupA);
        repository.save(groupB);
        repository.save(groupC);

        Optional<ProductGroup> found = repository.findFirstByName(name);
        Optional<ProductGroup> notFound = repository.findFirstByName(empty);
        ProductGroup create = repository.findFirstByName(empty).orElseGet(() -> new ProductGroup(tmp));

        // Then
        assertTrue(found.isPresent());
        assertFalse(notFound.isPresent());
        assertEquals(tmp, create.getName());

        // Clean
        repository.deleteById(groupA.getId());
        repository.deleteById(groupB.getId());
        repository.deleteById(groupC.getId());
    }
}
