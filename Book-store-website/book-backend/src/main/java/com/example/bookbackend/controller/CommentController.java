package com.example.bookbackend.controller;

import com.example.bookbackend.entity.Comment;
import com.example.bookbackend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("comment/book/{bookId}/user/{userId}")
    public Comment postComment(@PathVariable Integer bookId,@PathVariable Integer userId, @RequestBody String comment){
        return commentService.postComment(bookId,userId,comment);
    }
}
