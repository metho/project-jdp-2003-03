package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.config.security.Role;
import com.kodilla.ecommercee.entity.User;
import com.kodilla.ecommercee.exception.EntityAlreadyExistsException;
import com.kodilla.ecommercee.exception.EntityNotFoundException;
import com.kodilla.ecommercee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private static final String NO_ROLE = "ERROR: Authority '%s' not found. Only USER and ADMIN are available.";
    private static final String USER_EXIST = "ERROR: User '%s' already exist";
    private static final String USER_NOT_FOUND = "ERROR: User not found.";

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        if (!Role.exist(user.getAuthority())) {
            String message = String.format(NO_ROLE, user.getAuthority());
            throw new EntityNotFoundException(message);
        }
        if (userRepository.findByName(user.getName()) != null) {
            String message = String.format(USER_EXIST, user.getName());
            throw new EntityAlreadyExistsException(message);
        }
        String hashCode = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hashCode);
        return userRepository.save(user);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(USER_NOT_FOUND));
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException(USER_NOT_FOUND);
        } else {
            userRepository.deleteById(id);
        }
    }
}
