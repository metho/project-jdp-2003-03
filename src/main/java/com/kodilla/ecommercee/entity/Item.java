package com.kodilla.ecommercee.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Cart cart;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Product product;

    @Column(nullable = false)
    private double quantity;


    @Column(nullable = false)
    private double price;

    public Item(Cart cart, Product product, double quantity, double price) {
        this.cart = cart;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return Objects.equals(id, item.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
