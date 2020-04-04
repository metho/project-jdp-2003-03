package com.kodilla.ecommercee.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @OneToMany(mappedBy = "cart")
    private List<Item> items = new ArrayList<>();

    private boolean closed = false;

    @OneToOne(fetch = FetchType.EAGER)
    private UserOrder order;

}