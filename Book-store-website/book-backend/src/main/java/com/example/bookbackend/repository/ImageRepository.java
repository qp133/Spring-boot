package com.example.bookbackend.repository;

import com.example.bookbackend.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Integer> {
    List<Image> findByUser_IdOrderByUploadedAtDesc(Integer id);
}