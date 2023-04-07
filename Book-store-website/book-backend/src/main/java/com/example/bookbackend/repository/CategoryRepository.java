package com.example.bookbackend.repository;

import com.example.bookbackend.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Set<Category> findByIdIn(List<Integer> ids);

    Optional<Category> findByName(String name);

    long countByNameIgnoreCase(String name);
}