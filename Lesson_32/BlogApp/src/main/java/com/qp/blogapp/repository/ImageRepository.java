package com.qp.blogapp.repository;

import com.qp.blogapp.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Integer> {
}