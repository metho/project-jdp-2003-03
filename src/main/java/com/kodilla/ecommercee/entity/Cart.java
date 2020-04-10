package com.kodilla.ecommercee.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Cart {

    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    @OneToMany(mappedBy = "cart")
    private List<Item> items = new ArrayList<>();
    private boolean closed = false;


    public Cart(Long id, List<Item> items, boolean closed) {
        this.id = id;
        this.items = items;
        this.closed = closed;
    }
}