package com.kodilla.ecommercee.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@EqualsAndHashCode
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cart {
    @NonNull
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    @NonNull
    private UserOrder order;
    @NonNull
    @OneToMany(mappedBy = "cart")
    private List<Item> items = new ArrayList<>();
    @NonNull
    private boolean closed = false;



}