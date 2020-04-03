package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.dto.UserDto;
import com.kodilla.ecommercee.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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

    public UserDto translateToUserDto(User user) {return new UserDto();}

    public List<UserDto> translateToUserDtoList(List<User> users){
        return new ArrayList<>();
    }
}
