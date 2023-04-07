package com.example.bookbackend.controller;

import com.example.bookbackend.entity.Book;
import com.example.bookbackend.entity.Category;
import com.example.bookbackend.entity.Comment;
import com.example.bookbackend.entity.User;
import com.example.bookbackend.request.CreateAccountRequest;
import com.example.bookbackend.service.BookService;
import com.example.bookbackend.service.FileService;
import com.example.bookbackend.service.UserService;
import com.example.bookbackend.service.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequestMapping("api/v1")
public class WebController {

    @Autowired
    private WebService webService;

    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;
    @GetMapping("/books")
    public Page<Book> getBooks(@RequestParam(required = false) String search,
                               @RequestParam(required = false) String category,
                               @RequestParam(required = false)Integer page

                               ) {
        return webService.getBooks(search, category,page);
    }

    @GetMapping("/books/{id}/{slug}")
    public Book getBookById(@PathVariable Integer id) {
        return webService.getBookById(id);
    }

    // 3. Lấy ds tất cả category
    @GetMapping("/categories")
    public List<Category> getCategories() {
        return webService.getCategories();
    }
    //lấy sách theo author id
    @GetMapping("/books/author/{authorId}")
    public List<Book> getBooks(@PathVariable List<Integer> authorId){
        return webService.getBookByAuthorId(authorId);
    }
   //lấy comment theo book
    @GetMapping("/comments/{bookId}")
    public List<Comment> getCommentByBookId(@PathVariable Integer bookId){
        return webService.getCommentByBookId(bookId);
    }
    // Xem avatar
    @GetMapping(value = "/users/{id}/files/{fileId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] readAvatar(@PathVariable Integer id, @PathVariable Integer fileId) {
        return userService.readFile(id, fileId);
    }
    // Xem thumbnail
    @GetMapping(value = "/books/{id}/files/{fileId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] readFile(@PathVariable Integer id, @PathVariable Integer fileId) {
        return bookService.readThumbnail(id, fileId);
    }
    //create account
    @PostMapping("/handle-create-account")
    public User createAccount(@RequestBody CreateAccountRequest createAccountRequest){
        return userService.createAccount(createAccountRequest);
    }
}