package com.example.bookbackend.repository;

import com.example.bookbackend.entity.Author;
import com.example.bookbackend.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
    Set<Author> findByIdIn(List<Integer> ids);

    Optional<Author> findByName(String name);

    long countByNameIgnoreCase(String name);
}