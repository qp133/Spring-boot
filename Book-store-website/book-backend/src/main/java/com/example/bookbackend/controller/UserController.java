package com.example.bookbackend.controller;


import com.example.bookbackend.entity.User;
import com.example.bookbackend.request.UpdateImageRequest;
import com.example.bookbackend.request.UpdatePasswordRequest;
import com.example.bookbackend.request.UpsertUserRequest;
import com.example.bookbackend.response.UpdateAvatarResponse;
import com.example.bookbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api")
public class UserController {
    @Autowired
    private UserService userService;

    //1. Lấy danh sách user
    @GetMapping("admin/users")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    //2. Lấy cụ thể user theo id
    @GetMapping("admin/users/{id}")
    public User getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    //3. Tạo mới user
    @PostMapping("admin/users")
    public User createUser(@RequestBody UpsertUserRequest request) {
        return userService.createUser(request);
    }

    //4. Cập nhật user
    @PutMapping("admin/users/{id}")
    public User updateUser(@RequestBody UpsertUserRequest request, @PathVariable Integer id) {
        return userService.updateUser(request, id);
    }

    //5. Xóa user
    @DeleteMapping("admin/users/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
    }

    // Update password
    @PutMapping("/users/{id}/update-password")
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    public void updatePassword(@PathVariable Integer id, @RequestBody UpdatePasswordRequest request) {
        userService.updatePassword(id, request);
    }

    // Quên mật khẩu
    @PostMapping("/users/{id}/forgot-password")
    public String forgotPassword(@PathVariable Integer id) {
        return userService.forgotPassword(id);
    }

    // Upload ảnh
    @PostMapping("admin/users/{id}/update-avatar")
    public UpdateAvatarResponse uploadAvatar(@PathVariable Integer id, @ModelAttribute("file") MultipartFile file) {
        return new UpdateAvatarResponse(userService.uploadAvatar(id, file));
    }
    // Xóa file
    @DeleteMapping("admin/users/{id}/files/{fileId}")
    public void deleteFile(@PathVariable Integer id, @PathVariable Integer fileId) {
        userService.deleteFile(id, fileId);
    }


}