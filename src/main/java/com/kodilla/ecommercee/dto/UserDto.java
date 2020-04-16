package com.kodilla.ecommercee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String password;
    private String authority;
    private boolean blocked;
    private String address;
}
