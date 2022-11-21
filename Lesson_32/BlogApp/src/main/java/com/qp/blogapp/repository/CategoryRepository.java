package com.qp.blogapp.repository;

import com.qp.blogapp.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Set<Category> findByIdIn(List<Integer> ids);

    boolean existsByNameAllIgnoreCase(String name);

}