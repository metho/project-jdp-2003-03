package com.kodilla.ecommercee.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserOrder {

    @Id
    @GeneratedValue
    private Long Id;

    private LocalDate orderMade = LocalDate.now();

    private boolean resolved;

    @ManyToOne
    @JoinColumn(name = "user_ID")
    private User user;

    @OneToOne(fetch = FetchType.EAGER)
    @MapsId
    private Cart cart;

    public UserOrder(User user, Cart cart) {
        this.user = user;
        this.cart = cart;
    }
}
