package com.kodilla.ecommercee.dto;

import com.kodilla.ecommercee.entity.UserOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String password;
    private boolean blocked;
    private String address;
    private List<UserOrder> userOrders;

    public UserDto(String name, String password, boolean blocked, String address) {
        this.name = name;
        this.password = password;
        this.blocked = blocked;
        this.address = address;
    }
}
