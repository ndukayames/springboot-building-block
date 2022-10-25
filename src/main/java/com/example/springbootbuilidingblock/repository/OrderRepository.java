package com.example.springbootbuilidingblock.repository;

import com.example.springbootbuilidingblock.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
