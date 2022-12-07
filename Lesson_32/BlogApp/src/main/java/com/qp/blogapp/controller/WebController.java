package com.qp.blogapp.controller;

import com.qp.blogapp.entity.Blog;
import com.qp.blogapp.entity.Category;
import com.qp.blogapp.service.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class WebController {
    @Autowired
    private WebService webService;

    //1. Lấy ra tất cả bài viết đã public (status = true) v sắp xếp theo thời gian mới nhất
    //Bao gồm cả tìm kiếm theo tiêu đề và category name
    // /api/v1/blogs?search=java&category=backend
    @GetMapping("/blogs")
    public List<Blog> getBlogs(@RequestParam(required = false) String search,
                               @RequestParam(required = false) String category ){
        return webService.getBlogs(search, category);
    }

    //2. Lấy chi tiết bài viết
    // /api/v1/blogs/1/tong-ket-mon
    @GetMapping("/blogs/{id}/{slug}")
    public Blog getBlogs(@PathVariable Integer id) {
        return webService.getBlogById(id);
    }

    //3. Lấy danh sách tất cả category
    @GetMapping("/categories")
    public List<Category> getCategories() {
        return webService.getCategories();
    }
}
