package com.kodilla.ecommercee.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private Long userId;

    private String name;

    private String password;

    private boolean blocked;

    private String address;

    public User(String name, String password, boolean blocked, String address){
        this.name=name;
        this.password=password;
        this.blocked=blocked;
        this.address=address;
    }
}
