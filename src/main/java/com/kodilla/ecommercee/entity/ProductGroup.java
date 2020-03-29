package com.kodilla.ecommercee.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ProductGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    @OneToMany(mappedBy = "productGroup",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY)
    private List<ProductMock> products = new ArrayList<>();
}