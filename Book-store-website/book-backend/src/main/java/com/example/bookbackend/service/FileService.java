package com.example.bookbackend.service;


import com.example.bookbackend.entity.Book;
import com.example.bookbackend.entity.Image;
import com.example.bookbackend.entity.ImageBook;
import com.example.bookbackend.entity.User;
import com.example.bookbackend.exception.BadRequestException;
import com.example.bookbackend.exception.NotFoundException;
import com.example.bookbackend.repository.ImageBookRepository;
import com.example.bookbackend.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class FileService {
    private final ImageRepository imageRepository;
    private final ImageBookRepository imageBookRepository;
    // Upload file
    public String uploadFile(User user, MultipartFile file) {
        validateFile(file);

        try {
            Image image = new Image();
            image.setUploadedAt(LocalDateTime.now());
            image.setData(file.getBytes());
            image.setUser(user);

            imageRepository.save(image);
            return "http://localhost:8080/api/v1/users/" + user.getId() + "/files/" + image.getId();
        } catch (IOException e) {
            throw new RuntimeException("Lỗi khi upload file");
        }
    }

    // Check validate file
    public void validateFile(MultipartFile file) {
        String fileName = file.getOriginalFilename();

        // Kiểm tra tên file
        if (fileName == null || fileName.equals("")) {
            throw new BadRequestException("Tên file không hợp lệ");
        }

        // avatar.png -> png
        // image.jpg -> jpg
        // Kiểm tra đuôi file
        String fileExtension = getFileExtension(fileName);
        if (!checkFileExtension(fileExtension)) {
            throw new BadRequestException("Định dạng file không hợp lệ");
        }

        // Kiểm tra dung lượng file (<= 2MB)
        if ((double) file.getSize() / 1_048_576L > 2) {
            throw new BadRequestException("File không được vượt quá 2MB");
        }
    }

    // Lấy đuôi file
    private String getFileExtension(String fileName) {
        int lastIndexOf = fileName.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return "";
        }
        return fileName.substring(lastIndexOf + 1);
    }

    // Kiểm tra đuôi file có hợp lệ
    private boolean checkFileExtension(String fileExtension) {
        List<String> extensions = Arrays.asList("jpg", "png", "jpeg");
        return extensions.contains(fileExtension.toLowerCase());
    }

    // Đọc avatar
    public byte[] readFile(Integer fileId) {
        Image image = imageRepository.findById(fileId).orElseThrow(() -> {
            throw new NotFoundException("Not found image with id = " + fileId);
        });
        return image.getData();
    }
    // Đọc thumbnail
    public byte[] readThumbnail(Integer fileId) {
        ImageBook imageBook = imageBookRepository.findById(fileId).orElseThrow(() -> {
            throw new NotFoundException("Not found image of book with id = " + fileId);
        });
        return imageBook.getData();
    }
    // Lấy danh sách file của user
    public List<String> getFiles(Integer id) {
        List<Image> images = imageRepository.findByUser_IdOrderByUploadedAtDesc(id);
        return images.stream()
                .map(image -> "/api/v1/users/" + id + "/files/" + image.getId())
                .collect(Collectors.toList());
    }

    // Xóa file
    public void deleteFile(Integer fileId) {
        Image image = imageRepository.findById(fileId).orElseThrow(() -> {
            throw new NotFoundException("Not found image with id = " + fileId);
        });
        imageRepository.delete(image);
    }
    // Xóa file
    public void deleteThumbnail(Integer fileId) {
        ImageBook image = imageBookRepository.findById(fileId).orElseThrow(() -> {
            throw new NotFoundException("Not found thumbnail with id = " + fileId);
        });
        imageBookRepository.delete(image);
    }
    //uploadThumbnail
    public String uploadThumbnail(Book book, MultipartFile file) {
        validateFile(file);
        try {
            ImageBook imageBook = new ImageBook();
            imageBook.setUploadedAt(LocalDateTime.now());
            imageBook.setData(file.getBytes());
            imageBook.setBook(book);

            imageBookRepository.save(imageBook);
            return "http://localhost:8080/api/v1/books/" + book.getId() + "/files/" + imageBook.getId();
        } catch (IOException e) {
            throw new RuntimeException("Lỗi khi upload file");
        }
    }
}
