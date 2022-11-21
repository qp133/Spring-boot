package com.qp.blogapp.service;

import com.qp.blogapp.entity.Category;
import com.qp.blogapp.exception.BadRequestException;
import com.qp.blogapp.exception.NotFoundException;
import com.qp.blogapp.repository.CategoryRepository;
import com.qp.blogapp.request.UpsertCategoryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Integer id) {
        return categoryRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Not found category with id = " + id);
        });
    }

    public Category createCategory(UpsertCategoryRequest request) {
        Boolean checkCategory = categoryRepository.existsByNameAllIgnoreCase(request.getName());
        if(checkCategory) {
            throw new BadRequestException("Category " + request.getName() + " has already exist.");
        }
        Category category = Category.builder()
                .name(request.getName())
                .build();

        return categoryRepository.save(category);
    }

    public Category updateCategory(UpsertCategoryRequest request, Integer id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Not found category with id = " + id);
        });
        Boolean checkCategory = categoryRepository.existsByNameAllIgnoreCase(request.getName());
        if(checkCategory) {
            throw new BadRequestException("Category " + request.getName() + " has already exist.");
        }
        category.setName(request.getName());
        return categoryRepository.save(category);
    }

    public void deleteCategory(Integer id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Not found category with id = " + id);
        });
        categoryRepository.delete(category);
    }
}
