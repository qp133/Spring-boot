package com.example.bookbackend.controller;


import com.example.bookbackend.entity.Author;
import com.example.bookbackend.request.UpsertAuthorRequest;
import com.example.bookbackend.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/admin")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    // 1. Lấy danh sách author
    @GetMapping("authors")
    public List<Author> getCategories() {
        return authorService.getAuthor();
    }

    // 2. Lấy chi tiết author theo id
    @GetMapping("authors/{id}")
    public Author getCategoryById(@PathVariable Integer id) {
        return authorService.getAuthorById(id);
    }

    // 3. Tạo mới author
    @PostMapping("authors")
    public Author createCategory(@RequestBody UpsertAuthorRequest request) {
        return authorService.createAuthor(request);
    }

    // 4. Cập nhật author
    @PutMapping("authors/{id}")
    public Author updateAuthor(@RequestBody UpsertAuthorRequest request, @PathVariable Integer id) {
        return authorService.updateAuthor(id, request);
    }

    // 5. Xóa author
    @DeleteMapping("authors/{id}")
    public void deleteCategory(@PathVariable Integer id) {
        authorService.deleteAuthor(id);
    }
}