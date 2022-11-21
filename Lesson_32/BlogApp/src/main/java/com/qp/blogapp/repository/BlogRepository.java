package com.qp.blogapp.repository;

import com.qp.blogapp.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Integer> {

}