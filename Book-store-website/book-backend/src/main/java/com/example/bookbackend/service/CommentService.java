package com.example.bookbackend.service;

import com.example.bookbackend.entity.Book;
import com.example.bookbackend.entity.Comment;
import com.example.bookbackend.entity.User;
import com.example.bookbackend.exception.NotFoundException;
import com.example.bookbackend.repository.BookRepository;
import com.example.bookbackend.repository.CommentRepository;
import com.example.bookbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired 
    private CommentRepository commentRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;

    public Comment postComment(Integer bookId, Integer userId, String comment) {
        Comment newComment=new Comment();
        User user=userRepository.findById(userId).orElseThrow(() -> {
            throw new NotFoundException("Not found user with id = " + userId);
        });
        Book book=bookRepository.findById(bookId).orElseThrow(() -> {
            throw new NotFoundException("Not found book with id = " + bookId);
        });
        newComment.setBook(book);
        newComment.setUser(user);
        newComment.setContent(comment);

       return commentRepository.save(newComment);
    }



}
