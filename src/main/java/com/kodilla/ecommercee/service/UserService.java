package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.config.security.Role;
import com.kodilla.ecommercee.entity.User;
import com.kodilla.ecommercee.exception.EntityAlreadyExistsException;
import com.kodilla.ecommercee.exception.EntityNotFoundException;
import com.kodilla.ecommercee.exception.ExceptionType;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionType.USER_NOT_FOUND, id.toString()));
    }

    public User createUser(User user) {
        if (userRepository.findByName(user.getName()) != null) {
            throw new EntityAlreadyExistsException(ExceptionType.USER_FOUND, user.getName());
        }
        return saveUser(user);
    }

    public User updateUser(User user) {
        User presentUser = getUser(user.getId());
        if (!presentUser.getName().equals(user.getName())) {
            throw new EntityNotFoundException(ExceptionType.USER_CHANGE_NAME, user.getName());
        }
        return saveUser(user);
    }

    private User saveUser(User user) {
        if (!Role.exist(user.getAuthority())) {
            throw new EntityNotFoundException(ExceptionType.ROLE_NOT_FOUND, user.getAuthority());
        }
        String hashCode = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hashCode);
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        getUser(id);
        if (!orderRepository.existsByUser_Id(id)) {
            userRepository.deleteById(id);
        } else {
            throw new EntityAlreadyExistsException(ExceptionType.USER_BUSY, id.toString());
        }
    }
}
