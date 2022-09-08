package com.example.hw_lesson_2.repository;

import com.example.hw_lesson_2.entity.Book;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poiji.bind.Poiji;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Repository
public class BookRepository {
    private ArrayList<Book> book = new ArrayList<>();

    public BookRepository() {
        try {
            File file = ResourceUtils.getFile("classpath:static/book.json");
            ObjectMapper mapper = new ObjectMapper();
            book.addAll(mapper.readValue(file, new TypeReference<List<Book>>(){}));
            book.forEach(System.out::println);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Book> getAllBook() {
        return book;
    }

    public List<Book> getAllBookByExcel() {
        try {
            File file = ResourceUtils.getFile("classpath:static/book.xlsx");
            List<Book> listBook = Poiji.fromExcel(file, Book.class);
            return listBook;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public void sortByAuthor() {
        book.sort(new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return o1.getAuthor().compareTo(o2.getAuthor());
            }
        });
    }

    public void sortByTitle() {
        book.sort(new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return o1.getTitle().compareTo(o2.getTitle());
            }
        });
    }

    public void sortByYear() {
        book.sort(new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return o1.getYear() - o2.getYear();
            }
        });
    }
}
