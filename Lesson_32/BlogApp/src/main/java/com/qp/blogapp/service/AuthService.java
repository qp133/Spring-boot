package com.qp.blogapp.service;

import com.qp.blogapp.entity.User;
import com.qp.blogapp.request.LoginRequest;
import com.qp.blogapp.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;
    public User login(LoginRequest request, HttpSession session) {
        try {
            //Tạo đối tượng xác thực
            UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());

            //Tiến hành xác thực
            Authentication authentication = authenticationManager.authenticate(token);

            //Lưu vào trong Context
            SecurityContextHolder.getContext().setAuthentication(authentication);

            //Lưu vào trong session
            session.setAttribute("MY_SESSION", authentication.getName());

            CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return userDetails.getUser();

        }catch (AuthenticationException e) {
            throw new RuntimeException("Email or password incorrect.");
        }
    }

    public void logout(HttpSession session) {
        session.invalidate();
    }
}
