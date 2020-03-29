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
//    @JoinColumn(name = "ID")
//    private User user;

//    @OneToMany(targetEntity = Product.class,
//    orphanRemoval = true,
//    mappedBy = "userOrder",
//    fetch = FetchType.LAZY)
//    private List<Product> products;
}
