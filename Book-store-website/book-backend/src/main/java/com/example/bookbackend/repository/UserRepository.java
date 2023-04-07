package com.example.bookbackend.repository;

import com.example.bookbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByEmailAllIgnoreCase(String email);

    @Query("update User u set u.avatar = ?2 where u.id = ?1") // JPQL
    void updateAvatar(Integer id, String avatarUrl);
    boolean existsByNameAllIgnoreCase(String name);

    Optional<User> findByEmail(String email);
}