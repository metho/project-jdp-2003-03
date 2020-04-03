package com.kodilla.ecommercee.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cart {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "cart",
                fetch = FetchType.EAGER)
    private List<Item> items = new ArrayList<>();

    private boolean closed = false;

    @OneToOne(fetch = FetchType.EAGER)
    private UserOrder order;

}