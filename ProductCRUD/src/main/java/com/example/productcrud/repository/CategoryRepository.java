package com.example.productcrud.repository;

import com.example.productcrud.model.Category;
import com.example.productcrud.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CategoryRepository {
    private List<Category> categories = new ArrayList<>();

    public CategoryRepository() {
        List<Category> list = List.of(
                new Category(1, "Electronics"),
                new Category(2, "Household"),
                new Category(3, "Clothes")
        );
        for (Category category : list) {
            categories.add(category);
        }
    }

    public Optional<Category> get(int id) {
        return categories.stream().filter(c -> c.getId()==id).findFirst();
    }

    public List<Category> getAllCategories() {
        return this.categories;
    }

    public Category create(Category category) {
        int id;
        if (categories.isEmpty()) {
            id = 1;
        } else {
            Category lastCategory = categories.get(categories.size()-1);
            id = lastCategory.getId() + 1;
        }
        category.setId(id);
        categories.add(category);
        return category;
    }

    public Category edit(Category category) {
        get(category.getId()).ifPresent(existCategory -> {
            existCategory.setName(category.getName());
        });
        return category;
    }

    public void deleteById(int id) {
        get(id).ifPresent(exist -> categories.remove(exist));
    }

    public void delete(Category category) {
        deleteById(category.getId());
    }

    public Category search(String name) {
        return categories.stream().filter(i -> i.getName().contains(name)).findAny().orElse(null);
    }
}
