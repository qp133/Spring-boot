package com.example.bookbackend.service;


import com.example.bookbackend.entity.Author;
import com.example.bookbackend.entity.Category;
import com.example.bookbackend.exception.BadRequestException;
import com.example.bookbackend.exception.NotFoundException;
import com.example.bookbackend.repository.AuthorRepository;
import com.example.bookbackend.request.UpsertAuthorRequest;
import com.example.bookbackend.request.UpsertCategoryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> getAuthor() {
        return authorRepository.findAll();
    }

    public Author getAuthorById(Integer id) {
        return authorRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Not found author with id = " + id);
        });
    }

    public Author createAuthor(UpsertAuthorRequest request) {
        if (request.getName() == null || request.getName().equals("")) {
            throw new BadRequestException("Name is required");
        }

        if (authorRepository.findByName(request.getName()).isPresent()) {
            throw new BadRequestException("Author is exist");
        }

        Author author = new Author();
        author.setName(request.getName());

        return authorRepository.save(author);
    }

    public Author updateAuthor(Integer id, UpsertAuthorRequest request) {
        Author author = authorRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Not found author with id = " + id);
        });

        if (request.getName() == null || request.getName().equals("")) {
            throw new BadRequestException("Name is required");
        }

        if (authorRepository.findByName(request.getName())!=null
                && !authorRepository.findByName(request.getName()).get().getName().equals(author.getName())) {
            throw new BadRequestException("Author is exist");
        }

        author.setName(request.getName());
        return authorRepository.save(author);
    }

    public void deleteAuthor(Integer id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Not found author with id = " + id);
        });

        authorRepository.delete(author);
    }
}