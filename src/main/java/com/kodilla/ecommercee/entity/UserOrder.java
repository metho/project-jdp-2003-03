package com.kodilla.ecommercee.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


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

//    @ManyToOne
//    @JoinColumn(name = "user_ID")
//    private User user;

    @OneToMany(orphanRemoval = true,
            mappedBy = "userOrder",
            targetEntity = Product.class,
            fetch = FetchType.LAZY)
    private List<Product> products;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "cart_ID")
    private Cart cart;
}
