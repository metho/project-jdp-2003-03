package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.UserDto;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping
    public List<UserDto> getUsers() {
        log.info("Get list of users:");
        return userMapper.toUserDtoList(userService.getUsers());
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable Long id) {
        log.info("Get user by ID = " + id);
        return userMapper.toUserDto(userService.getUser(id));
    }

    @PostMapping
    public void createUser(@RequestBody UserDto userDto) {
        log.info("Create user: " + userDto.getName());
        userService.saveUser(userMapper.toUser(userDto));
    }

    @PutMapping
    public UserDto updateUser(@RequestBody UserDto userDto) {
        log.info("Update user " + userDto.getId() + ", name:" + userDto.getName());
        return userMapper.toUserDto(userService.saveUser(userMapper.toUser(userDto)));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        log.info("Delete user by Id: " + id);
        userService.deleteUser(id);
    }
}


