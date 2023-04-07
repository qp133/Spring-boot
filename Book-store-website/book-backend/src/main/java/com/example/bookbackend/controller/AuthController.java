package com.example.bookbackend.controller;

import com.example.bookbackend.entity.User;
import com.example.bookbackend.request.CreateAccountRequest;
import com.example.bookbackend.request.LoginRequest;
import com.example.bookbackend.response.AuthResponse;
import com.example.bookbackend.service.AuthService;
import com.example.bookbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;
    @Autowired
    private UserService userService;
    @PostMapping("handle-login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @GetMapping("/handle-logout")
    public String logout(HttpSession session) {
        return authService.logout(session);
    }

}