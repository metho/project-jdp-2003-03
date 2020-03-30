package com.kodilla.ecommercee.entity;

import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    UserRepository repository;

    @Test
    public void testUserGetAll() {
        //Given
        User use1 = new User("User1", "password1", false, "address1");
        User use2 = new User("User2", "password2", false, "address2");
        User use3 = new User("User3", "password3", false, "address3");
        //When
        repository.save(use1);
        repository.save(use2);
        repository.save(use3);
        //Then
        Assert.assertEquals(3, repository.findAll().size());

        repository.deleteAll();
    }

    @Test
    public void testUserDelete() {
        //Given
        User use1 = new User("User1", "password1", false, "address1");
        User use2 = new User("User2", "password2", false, "address2");
        User use3 = new User("User3", "password3", false, "address3");
        //When
        repository.save(use1);
        repository.save(use2);
        repository.save(use3);
        Long id = use1.getId();
        repository.deleteById(id);
        //Then
        Assert.assertEquals(2, repository.findAll().size());

        repository.deleteAll();
    }

    @Test
    public void testUserGetOne() {
        //Given
        User use1 = new User("User1", "password1", false, "address1");
        User use2 = new User("User2", "password2", false, "address2");
        User use3 = new User("User3", "password3", false, "address3");
        //When
        repository.save(use1);
        repository.save(use2);
        repository.save(use3);

        Long id = use1.getId();
        //Then
        Assert.assertTrue(repository.findById(id).isPresent());

        repository.deleteAll();

    }

}

