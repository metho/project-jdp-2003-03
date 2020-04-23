package com.kodilla.ecommercee.entity;

import com.kodilla.ecommercee.config.security.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String password;

    private String authority = Role.USER.name();

    private boolean blocked;

    private String address;

    @OneToMany(mappedBy = "user")
    private List<UserOrder> userOrders;

    public User(String name, String password, String authority, boolean blocked, String address) {
        this.name = name;
        this.password = password;
        this.authority = authority;
        this.blocked = blocked;
        this.address = address;
    }

    public User(Long id, String name, String password, String authority, boolean blocked, String address) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.authority = authority;
        this.blocked = blocked;
        this.address = address;
    }

    public String getCodePassword(){
        return "***";
    }
}
