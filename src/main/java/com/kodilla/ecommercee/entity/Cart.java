package com.kodilla.ecommercee.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


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
                fetch = FetchType.LAZY)
    private List<Item> items = new ArrayList<>();

    private boolean closed = false;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Cart)) return false;
        Cart cart = (Cart) o;
        return closed == cart.closed &&
                Objects.equals(id, cart.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, closed);
    }
}