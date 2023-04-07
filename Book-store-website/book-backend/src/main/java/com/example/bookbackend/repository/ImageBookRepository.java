package com.example.bookbackend.repository;

import com.example.bookbackend.entity.Image;
import com.example.bookbackend.entity.ImageBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageBookRepository extends JpaRepository<ImageBook, Integer> {
    List<ImageBook> findByBook_IdOrderByUploadedAtDesc(Integer id);

}