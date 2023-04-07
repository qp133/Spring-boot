package com.example.bookbackend.controller;

import com.example.bookbackend.entity.Book;
import com.example.bookbackend.request.UpsertBookRequest;
import com.example.bookbackend.response.UpdateAvatarResponse;
import com.example.bookbackend.response.UpdateThumbnailResponse;
import com.example.bookbackend.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/admin")
public class BookController {

    @Autowired
    private BookService bookService =new BookService();

    @GetMapping("books")
    public Page<Book> getBooks(@RequestParam(required = false)Integer page) {

        return bookService.getBooks(page);
    }

    //2. Lấy chi tiết book
    @GetMapping("/books/{id}")
    public Book getBookById(@PathVariable Integer id) {
        return bookService.getBookById(id);
    }

    //3. Tạo Book
    @PostMapping("books")
    public Book createBook(@RequestBody UpsertBookRequest request) {
        return bookService.createBook(request);
    }

    //4. Cập nhật Book
    @PutMapping("books/{id}")
    public Book updateBook(@PathVariable Integer id, @RequestBody UpsertBookRequest request) {
        return bookService.updateBook(id,request);
    }

    //5. Xóa Book
    @DeleteMapping("books/{id}")
    public void deleteBook(@PathVariable Integer id) {
        bookService.deleteBook(id);
    }

    // Upload thumbnail
    @PostMapping("/books/{id}/update-thumbnail")
    public Book uploadThumbnail(@PathVariable Integer id, @ModelAttribute("file") MultipartFile file) {
        return  bookService.uploadThumbnail(id, file);
    }

}