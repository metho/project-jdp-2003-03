package com.kodilla.ecommercee.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


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

    @NonNull
    @OneToMany(mappedBy = "cart")
    private List<Item> items = new ArrayList<>();
    @NonNull
    private boolean closed = false;



}