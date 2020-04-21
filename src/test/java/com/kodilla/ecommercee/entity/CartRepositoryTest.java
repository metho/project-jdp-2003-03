package com.kodilla.ecommercee.entity;

import com.kodilla.ecommercee.repository.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CartRepositoryTest {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ProductGroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Before
    public void printBefore() {
        System.out.println("\nStart test");
    }

    @Test
    public void testSaveAndGetCart() {
        // Given
        Cart cartA = new Cart();
        Cart cartB = new Cart();
        Cart cartC = new Cart();

        // When
        System.out.println("Test get all records ...\n");
        cartRepository.save(cartA);
        Long firstId = cartA.getId();
        cartRepository.save(cartB);
        cartRepository.save(cartC);

        Cart cart = cartRepository.getOne(firstId);
        ArrayList<Cart> newList = new ArrayList<>();
        newList.add(cart);

        // Then
        assertEquals(3, cartRepository.count());
        assertEquals(1, newList.size());

        // Clean
        cartRepository.deleteById(cartA.getId());
        cartRepository.deleteById(cartB.getId());
        cartRepository.deleteById(cartC.getId());
    }

    @Test
    public void testUpdateCart() {
        // Given
        Cart cartA = new Cart();
        Cart cartB = new Cart();
        Cart cartC = new Cart();

        // When
        System.out.println("Test update records ...\n");
        cartRepository.save(cartA);
        cartRepository.save(cartB);
        cartRepository.save(cartC);

        Long id = cartB.getId();
        Cart cart = cartRepository.findById(id).orElseThrow(RuntimeException::new);
        cart.setClosed(true);
        cartRepository.save(cart);
        Cart found = cartRepository.findById(id).orElseThrow(RuntimeException::new);

        // Then
        assertTrue(found.isClosed());

        // Clean
        cartRepository.deleteById(cartA.getId());
        cartRepository.deleteById(cartB.getId());
        cartRepository.deleteById(cartC.getId());
    }

    @Test
    public void testDeleteCart() {
        // Given
        Cart cartA = new Cart();
        Cart cartB = new Cart();
        Cart cartC = new Cart();

        // When
        System.out.println("Test delete records ...\n");
        cartRepository.save(cartA);
        cartRepository.save(cartB);
        cartRepository.save(cartC);

        Long id = cartB.getId();
        cartRepository.deleteById(id);
        Optional<Cart> found = cartRepository.findById(id);

        // Then
        assertFalse(found.isPresent());

        // Clean
        cartRepository.deleteById(cartA.getId());
        cartRepository.deleteById(cartC.getId());
    }

    @Test
    public void testExistsByUserId() {
        // Given
        User user = new User("Jacek", "aaa", "ADMIN", false, "none");
        Cart cart = new Cart();
        UserOrder order = new UserOrder(LocalDate.now(), false, false, user, cart);

        // When
        System.out.println("Test exist by userId ...\n");
        userRepository.save(user);
        cartRepository.save(cart);
        orderRepository.save(order);

        boolean found = orderRepository.existsByUser_Id(user.getId());
        boolean notFound = orderRepository.existsByUser_Id(user.getId() + 1);

        // Then
        assertTrue(found);
        assertFalse(notFound);

        // Clean
        orderRepository.deleteById(order.getId());
        cartRepository.deleteById(cart.getId());
        userRepository.deleteById(user.getId());
    }

    @Test
    public void testCartAndItem() {
        // Given
        Cart cart = new Cart();
        ProductGroup group = new ProductGroup("Empty");
        Product product = new Product("AAA", 2.0, group);
        Item itemA = new Item(cart, product, 2.0,3.0);
        Item itemB = new Item(cart, product, 1.0,9.0);
        Item itemC = new Item(cart, product, 0.5, 7.8);
        cart.getItems().addAll(Arrays.asList(itemA, itemB, itemC));
        product.getItems().addAll(Arrays.asList(itemA, itemB, itemC));
        group.getProducts().add(product);

        // When
        System.out.println("Test cart and item ...\n");
        groupRepository.save(group);
        productRepository.save(product);
        cartRepository.save(cart);
        itemRepository.save(itemA);
        itemRepository.save(itemB);
        itemRepository.save(itemC);

        Long id = cart.getId();
        Optional<Cart> foundCart = cartRepository.findById(id);
        List<Item> items = itemRepository.findAll();

        Long idDel = itemB.getId();
        itemRepository.deleteById(idDel);
        List<Item> itemsDel = itemRepository.findAll();

        // Then
        assertEquals(3, items.size());
        assertTrue(foundCart.isPresent());
        assertEquals(3, foundCart.get().getItems().size());
        assertEquals(2, itemsDel.size());

        // Clean
        itemRepository.deleteById(itemA.getId());
        itemRepository.deleteById(itemC.getId());
        productRepository.deleteById(product.getId());
        groupRepository.deleteById(group.getId());
    }
}
