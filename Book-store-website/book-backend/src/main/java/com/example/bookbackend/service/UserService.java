package com.example.bookbackend.service;


import com.example.bookbackend.entity.User;
import com.example.bookbackend.exception.BadRequestException;
import com.example.bookbackend.exception.NotFoundException;
import com.example.bookbackend.repository.UserRepository;
import com.example.bookbackend.request.CreateAccountRequest;
import com.example.bookbackend.request.UpdateImageRequest;
import com.example.bookbackend.request.UpdatePasswordRequest;
import com.example.bookbackend.request.UpsertUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private FileService fileService;
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Not found user with id = " + id);
        });
    }
//thêm user
    public User createUser(UpsertUserRequest request) {
        Boolean checkUser = userRepository.existsByNameAllIgnoreCase(request.getName());
        if (checkUser) {
            throw new BadRequestException("User " + request.getName() + " has already exist.");
        }
        Boolean checkEmail=userRepository.existsByEmailAllIgnoreCase(request.getEmail());
        if (checkEmail) {
            throw new BadRequestException("Email " + request.getEmail() + " has already exist.");
        }
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .avatar(request.getAvatar())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(request.getRoles())
                .build();
        return userRepository.save(user);
    }
//update user
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
        user.setRoles(request.getRoles());

        return userRepository.save(user);
    }
//xóa user
    public void deleteUser(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Not found user with id = " + id);
        });
        String img=user.getAvatar();
        if (img!=null&&img.contains("/api/v1")){
            int index=img.lastIndexOf("/");
            String fileId= img.substring(index+1);
//            fileService.deleteFile(fileId-'0');
            deleteFile(id,Integer.parseInt(fileId));
        }
        userRepository.delete(user);
    }

    // Upload avatar trực tiếp
    public String uploadAvatar(Integer id, MultipartFile file) {
        User user = userRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Not found user with id = " + id);
        });

        String filePath = fileService.uploadFile(user, file);
        user.setAvatar(filePath);
        userRepository.save(user);

        return filePath;
    }

    // Đọc file
    public byte[] readFile(Integer id, Integer fileId) {
        User user = userRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Not found user with id = " + id);
        });
        return fileService.readFile(fileId);
    }

    // Thay đổi mật khẩu
    public void updatePassword(Integer id, UpdatePasswordRequest request) {
        User user = userRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Not found user with id = " + id);
        });

        // Kiểm tra xem password cũ có chính xác hay không
        if(!user.getPassword().equals(request.getOldPassword())) {
            throw new BadRequestException("Mật khẩu cũ không chính xác");
        }

        // Kiểm tra xem password cũ và mới có trùng nhau hay không
        if(request.getOldPassword().equals(request.getNewPassword())) {
            throw new BadRequestException("Mật khẩu cũ và mới không được trùng nhau");
        }

        user.setPassword(request.getNewPassword());
        userRepository.save(user);
    }

    /// Quên mật khẩu
    public String forgotPassword(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Not found user with id = " + id);
        });

        // Generate password
        String newPassword = UUID.randomUUID().toString();

        user.setPassword(newPassword);
        userRepository.save(user);

        return newPassword;
    }

    // Xóa file
    public void deleteFile(Integer id, Integer fileId) {
        User user = userRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Not found user with id = " + id);
        });
        fileService.deleteFile(fileId);
    }

    public User createAccount(CreateAccountRequest createAccountRequest) {
        Boolean checkUser = userRepository.existsByNameAllIgnoreCase(createAccountRequest.getName());
        if (checkUser) {
            throw new BadRequestException("User " + createAccountRequest.getName() + " has already exist.");
        }
        Boolean checkEmail=userRepository.existsByEmailAllIgnoreCase(createAccountRequest.getEmail());
        if (checkEmail) {
            throw new BadRequestException("Email " + createAccountRequest.getEmail() + " has already exist.");
        }
        if (!Objects.equals(createAccountRequest.getPassword(), createAccountRequest.getRepeatPassword())){
            throw new BadRequestException("Password and repeat password is not match");
        }
        if(createAccountRequest.getName().equals("")||createAccountRequest.getEmail().equals("")||createAccountRequest.getPassword().equals("")||createAccountRequest.getRepeatPassword().equals("")){
            throw new BadRequestException("Không được để trống thông tin");
        }
        User user = User.builder()
                .name(createAccountRequest.getName())
                .email(createAccountRequest.getEmail())
                .password(passwordEncoder.encode(createAccountRequest.getPassword()))
                .roles(List.of("USER"))
                .build();
        return userRepository.save(user);
    }
}