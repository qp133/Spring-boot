package com.qp.blogapp.repository;

import com.qp.blogapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    boolean existsByNameAllIgnoreCase(String name);
}