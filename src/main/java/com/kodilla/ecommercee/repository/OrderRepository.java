package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.entity.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<UserOrder, Long> {

    List<UserOrder> findByMailSentFalse();
    boolean existsByUser_Id(Long id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE UserOrder SET mailSent = :value WHERE id = :id")
    void setMailSent(@Param("id") Long id, @Param("value") boolean value);
}
