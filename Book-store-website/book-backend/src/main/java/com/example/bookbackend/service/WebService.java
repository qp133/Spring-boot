package com.example.bookbackend.service;


import com.example.bookbackend.entity.Book;
import com.example.bookbackend.entity.Category;
import com.example.bookbackend.entity.Comment;
import com.example.bookbackend.exception.NotFoundException;
import com.example.bookbackend.repository.BookRepository;
import com.example.bookbackend.repository.CategoryRepository;
import com.example.bookbackend.repository.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
@Slf4j
public class WebService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CommentRepository commentRepository;

    public Page<Book> getBooks(String search, String category, Integer page) {
        log.info("search {}", search);
        log.info("category {}", category);
        if (page==null){
            page=1;
        }
        return bookRepository.findByTitleContainsIgnoreCaseAndCategories_NameIgnoreCase(search, category,PageRequest.of(page-1, 12));
    }

    public Book getBookById(Integer id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Not found book with id = " + id);
        });

        return book;
    }

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public List<Book> getBookByAuthorId(List<Integer> authorId) {
        return bookRepository.findByAuthors_IdIn(authorId);
    }

    public List<Comment> getCommentByBookId(Integer bookId) {
        return commentRepository.findByBook_IdOrderByCreatedAtDesc(bookId);
    }
}