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
import java.util.Optional;

@Service
public class UserService {
    private static final String NO_ROLE = "ERROR: Authority '%s' not found. Only USER and ADMIN are available.";
    private static final String USER_EXIST = "ERROR: User '%s' already exist";

    @Autowired
    private UserRepository userRepository;

    public void saveUser(User user) {
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
        userRepository.save(user);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
