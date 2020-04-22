package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.dto.UserDto;
import com.kodilla.ecommercee.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public User mapToUser(final UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getName(),
                userDto.getPassword(),
                userDto.getAuthority(),
                userDto.isBlocked(),
                userDto.getAddress());
    }

    public UserDto mapToUserDto(final User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getPassword().replaceAll("[^a-zA-Z0-9]", "").replaceAll("[a-zA-Z0-9]", ""),
                user.getAuthority(),
                user.isBlocked(),
                user.getAddress());
    }

    public List<UserDto> mapToUserDtoList(List<User> users) {
        return users.stream()
                .map(this::mapToUserDto)
                .collect(Collectors.toList());
    }
}
