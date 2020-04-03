package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.dto.UserDto;
import com.kodilla.ecommercee.entity.User;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public User translateToUser(final UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getName(),
                userDto.getPassword(),
                userDto.isBlocked(),
                userDto.getAddress());
    }

    public UserDto translateToUserDto(final User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getPassword(),
                user.isBlocked(),
                user.getAddress());
    }

    public List<UserDto> translateToUserDtoList(List<User> users) {
        return users.stream()
                .map(user -> new UserDto(user.getId(), user.getName(), user.getPassword(), user.isBlocked(), user.getAddress()))
                .collect(Collectors.toList());
    }

}
