package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    boolean existsByProductId(Long id);
}
