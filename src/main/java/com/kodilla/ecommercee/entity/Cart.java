package com.kodilla.ecommercee.entity;

import com.kodilla.ecommercee.GenericEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cart {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany
    private List<GenericEntity> products;


}