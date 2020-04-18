package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.entity.Product;
import com.kodilla.ecommercee.entity.ProductGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Modifying
    @Query(value = "UPDATE Product SET productGroup = :newGroup WHERE productGroup = :oldGroup")
    void updateGroupId(@Param("newGroup") ProductGroup newGroup, @Param("oldGroup") ProductGroup oldGroup);
}
