package com.qp.blogapp.service;

import com.qp.blogapp.entity.Blog;
import com.qp.blogapp.entity.Category;
import com.qp.blogapp.exception.NotFoundException;
import com.qp.blogapp.repository.BlogRepository;
import com.qp.blogapp.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebService {
    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Blog> getBlogs(String search, String category) {
        return blogRepository.findByTitleContainsIgnoreCaseAndStatusTrueAndCategories_NameIgnoreCaseOrderByPublishedAtDesc(search, category);
    }

    public Blog getBlogById(Integer id) {
        Blog blog = blogRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Not found blog with id = " + id); 
        });
        if(!blog.getStatus()) {
            throw new NotFoundException("Not found blog with id = "+ id);
        }
        
        //Trả về thông tin bài viết đã public
        return blog;
    }

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }
}
