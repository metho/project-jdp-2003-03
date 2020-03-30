package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.entity.ProductGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductGroupRepository extends JpaRepository<ProductGroup, Long> {
    boolean existsByName(String name);
    boolean existsById(Long id);
    Optional<ProductGroup> findFirstByName(String name);
}
