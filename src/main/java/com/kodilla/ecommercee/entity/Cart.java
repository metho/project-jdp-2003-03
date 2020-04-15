package com.kodilla.ecommercee.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private Long id;

    @NonNull
    @OneToMany(mappedBy = "cart",cascade = CascadeType.REMOVE)
    private List<Item> items = new ArrayList<>();

    private boolean closed;



}