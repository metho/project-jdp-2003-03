package com.kodilla.ecommercee.entity;

import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserOrderRepositoryTest {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CartRepository cartRepository;

    @Before
    public void printBefore() {
        System.out.println("\nStart test");
    }

    @Test
    public void testOrderSave() {
        // Give
        User user = new User("Jack", "None", false, "None");
        Cart cartOne = new Cart();
        Cart cartTwo = new Cart();
        Cart cartThree = new Cart();
        UserOrder orderOne = new UserOrder(user, cartOne);
        UserOrder orderTwo = new UserOrder(user, cartTwo);
        UserOrder orderThree = new UserOrder(user, cartThree);

        // When
        System.out.println("Test save and get all records ...\n");
        userRepository.save(user);
        cartRepository.save(cartOne);
        cartRepository.save(cartTwo);
        cartRepository.save(cartThree);
        orderRepository.save(orderOne);
        orderRepository.save(orderTwo);
        orderRepository.save(orderThree);

        long count = orderRepository.count();
        long cartCount = cartRepository.count();

        // Then
        assertEquals(user.getId(), orderOne.getUser().getId());
        assertEquals(cartOne.getId(), orderOne.getCart().getId());
        assertEquals(3, count);
        assertEquals(3, cartCount);

        // Clean
        orderRepository.deleteById(orderOne.getId());
        orderRepository.deleteById(orderTwo.getId());
        orderRepository.deleteById(orderThree.getId());
        cartRepository.deleteById(cartOne.getId());
        cartRepository.deleteById(cartTwo.getId());
        cartRepository.deleteById(cartThree.getId());
        userRepository.deleteById(user.getId());
    }

    @Test
    public void testOrderUpdate() {
        // Give
        User user = new User("Jack", "None", false, "None");
        Cart cart = new Cart();
        UserOrder order = new UserOrder(user, cart);

        // When
        System.out.println("Test update record ...\n");
        userRepository.save(user);
        cartRepository.save(cart);
        orderRepository.save(order);

        cart.setClosed(true);
        cartRepository.save(cart);
        Optional<UserOrder> getOrder = orderRepository.findById(cart.getId());

        // Then
        assertTrue(getOrder.isPresent());
        assertTrue(getOrder.get().getCart().isClosed());

        // Clean
        orderRepository.deleteById(order.getId());
        cartRepository.deleteById(cart.getId());
        userRepository.deleteById(user.getId());
    }

    @Test
    public void testOrderDelete() {
        // Give
        User user = new User("Jack", "None", false, "None");
        Cart cartOne = new Cart();
        Cart cartTwo = new Cart();
        Cart cartThree = new Cart();
        UserOrder orderOne = new UserOrder(user, cartOne);
        UserOrder orderTwo = new UserOrder(user, cartTwo);
        UserOrder orderThree = new UserOrder(user, cartThree);

        // When
        System.out.println("Test delete record ...\n");
        userRepository.save(user);
        cartRepository.save(cartOne);
        cartRepository.save(cartTwo);
        cartRepository.save(cartThree);
        orderRepository.save(orderOne);
        orderRepository.save(orderTwo);
        orderRepository.save(orderThree);

        long countBefore = orderRepository.count();
        orderRepository.deleteById(orderTwo.getId());
        long countAfter = orderRepository.count();
        long cartCount = cartRepository.count();

        // Then
        assertEquals(3, countBefore);
        assertEquals(2, countAfter);
        assertEquals(3, cartCount);

        // Clean
        orderRepository.deleteById(orderOne.getId());
        orderRepository.deleteById(orderThree.getId());
        cartRepository.deleteById(cartOne.getId());
        cartRepository.deleteById(cartTwo.getId());
        cartRepository.deleteById(cartThree.getId());
        userRepository.deleteById(user.getId());
    }
}
