package com.kodilla.ecommercee.repository;
import com.kodilla.ecommercee.entity.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<UserOrder, Long> {
}
