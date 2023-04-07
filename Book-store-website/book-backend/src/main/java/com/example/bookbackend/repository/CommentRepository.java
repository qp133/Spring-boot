package com.example.bookbackend.repository;

import com.example.bookbackend.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {


    List<Comment> findByBook_IdOrderByCreatedAtDesc(Integer id);

}