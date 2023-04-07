package com.example.bookbackend.request;

import com.example.bookbackend.entity.Book;
import com.example.bookbackend.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpsertOrderItemRequest {
    private Book book;
    private Integer amount;
    private User user;
}
