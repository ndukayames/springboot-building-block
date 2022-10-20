package com.example.springbootbuilidingblock.repository;

import com.example.springbootbuilidingblock.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// extend JpaRepository<ModelName, datatype of the model's primary key>
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
