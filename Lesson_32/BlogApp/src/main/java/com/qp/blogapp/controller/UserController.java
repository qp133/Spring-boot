package com.qp.blogapp.controller;

import com.qp.blogapp.entity.User;
import com.qp.blogapp.request.UpsertUserRequest;
import com.qp.blogapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/admin")
public class UserController {
    @Autowired
    private UserService userService;

    //1. Lấy danh sách user
    @GetMapping("users")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    //2. Lấy cụ thể user theo id
    @GetMapping("users/{id}")
    public User getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    //3. Tạo mới user
    @PostMapping("users")
    public User createUser(@RequestBody UpsertUserRequest request) {
        return userService.createUser(request);
    }

    //4. Cập nhật user
    @PutMapping("users/{id}")
    public User updateUser(@RequestBody UpsertUserRequest request, @PathVariable Integer id) {
        return userService.updateUser(request, id);
    }

    //5. Xóa user
    @DeleteMapping("users/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
    }
}
