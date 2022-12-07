package com.qp.blogapp.controller;

import com.qp.blogapp.entity.User;
import com.qp.blogapp.request.LoginRequest;
import com.qp.blogapp.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("handle-login")
    public User login(@RequestBody LoginRequest request, HttpSession session) {
        return authService.login(request, session);
    }

    @GetMapping("handle-logout")
    public void logout(HttpSession session) {
        authService.logout(session);
    }
}
