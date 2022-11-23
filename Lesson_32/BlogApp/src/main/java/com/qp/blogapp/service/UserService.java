package com.qp.blogapp.service;

import com.qp.blogapp.entity.User;
import com.qp.blogapp.exception.BadRequestException;
import com.qp.blogapp.exception.NotFoundException;
import com.qp.blogapp.repository.UserRepository;
import com.qp.blogapp.request.UpsertUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Not found user with id = " + id);
        });
    }

    public User createUser(UpsertUserRequest request) {
        Boolean checkUser = userRepository.existsByNameAllIgnoreCase(request.getName());
        if (checkUser) {
            throw new BadRequestException("User " + request.getName() + " has already exist.");
        }
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .avatar(request.getAvatar())
                .password(request.getPassword())
                .roles(request.getRoles())
                .build();
        return userRepository.save(user);
    }

    public User updateUser(UpsertUserRequest request, Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Not found user with id = " + id);
        });
        Boolean checkUser = userRepository.existsByNameAllIgnoreCase(request.getName());
        if (checkUser) {
            throw new BadRequestException("User " + request.getName() + " has already exist.");
        }
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setAvatar(request.getAvatar());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(request.getRoles());

        return userRepository.save(user);
    }

    public void deleteUser(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Not found user with id = " + id);
        });
        userRepository.delete(user);
    }
}
