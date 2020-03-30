package com.kodilla.ecommercee.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserOrder {

    @Id
    @GeneratedValue
    private Long Id;

    private LocalDate orderMade;

    private boolean resolved;

    @ManyToOne
    @JoinColumn(name = "user_ID")
    private User user;

    @OneToOne(fetch = FetchType.EAGER)
    @MapsId
    private Cart cart;
}
