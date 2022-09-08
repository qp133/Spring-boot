package com.example.hw_lesson_2.controller;

import com.example.hw_lesson_2.entity.Book;
import com.example.hw_lesson_2.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping
public class BookController {

    @Autowired
    private BookRepository bookRepo;
    @GetMapping("/bookjson")
    public ResponseEntity<List<Book>> getJSON() {
        return ResponseEntity.ok().body(bookRepo.getAllBook());
    }

    @GetMapping("/bookexcel")
    public ResponseEntity<?> getEXCEL() {
        List<Book> book = bookRepo.getAllBookByExcel();
        System.out.println(book);
        return ResponseEntity.ok().body(book);
    }

    private ArrayList<Book> bookArrayList;

    @GetMapping("/sortByAuthor")
    public ArrayList<Book> sortByAuthor() {
        bookArrayList = (ArrayList<Book>) bookRepo.getAllBook();
        bookRepo.sortByAuthor();
        return bookArrayList;
    }

    @GetMapping("/sortByTitle")
    public ArrayList<Book> sortByTitle() {
        bookArrayList = (ArrayList<Book>) bookRepo.getAllBook();
        bookRepo.sortByTitle();
        return bookArrayList;
    }

    @GetMapping("/sortByYear")
    public ArrayList<Book> sortByYear() {
        bookArrayList = (ArrayList<Book>) bookRepo.getAllBook();
        bookRepo.sortByYear();
        return bookArrayList;
    }
}
