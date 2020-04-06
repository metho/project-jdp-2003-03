package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.UserDto;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping
    public List<UserDto> getUsers() {
        return userMapper.toUserDtoList(userService.getUsers());
    }

    @GetMapping(value = "/{id}")
    public UserDto getUser(@PathVariable Long id) throws UserNotFoundException {
        return userMapper.toUserDto(userService.getUser(id).orElseThrow(UserNotFoundException::new));
    }

    @PostMapping
    public void createUser(@RequestBody UserDto userDto) {
        userService.saveUser(userMapper.toUser(userDto));
    }

    @PutMapping
    public void updateUser(@RequestBody UserDto userDto) {
        userService.saveUser(userMapper.toUser(userDto));
    }

    @DeleteMapping
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}


