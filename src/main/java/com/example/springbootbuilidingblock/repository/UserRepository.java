package com.example.springbootbuilidingblock.repository;

import com.example.springbootbuilidingblock.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// extend JpaRepository<ModelName, datatype of the model's primary key>
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
