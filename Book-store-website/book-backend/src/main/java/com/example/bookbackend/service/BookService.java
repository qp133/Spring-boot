package com.example.bookbackend.service;

import com.example.bookbackend.entity.Author;
import com.example.bookbackend.entity.Book;
import com.example.bookbackend.entity.Category;
import com.example.bookbackend.exception.NotFoundException;
import com.example.bookbackend.repository.AuthorRepository;
import com.example.bookbackend.repository.BookRepository;
import com.example.bookbackend.repository.CategoryRepository;
import com.example.bookbackend.request.UpsertBookRequest;
import com.github.slugify.Slugify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    private Slugify slugify;

    @Autowired
    private FileService fileService;

    public Page<Book> getBooks(Integer page) {
        if (page==null){
            page=1;
        }
        return bookRepository.findByOrderByCreatedAtDesc( PageRequest.of(page-1, 10));
    }

    public Book getBookById(Integer id) {
        return bookRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Not found book with id = " + id);
        });
    }

    public Book createBook(UpsertBookRequest request) {
        // Lấy ra ds category tương ứng (từ ds id gửi lên)
        Set<Category> categories = categoryRepository.findByIdIn(request.getCategoryIds());
        // Lấy ra ds author tương ứng (từ ds id gửi lên)
        Set<Author> authors = authorRepository.findByIdIn(request.getAuthorIds());
        //tao book
        Book book=Book.builder()
                .title(request.getTitle())
                .slug(slugify.slugify(request.getTitle()))
                .description(request.getDescription())
                .categories(categories)
                .authors(authors)
                .thumbnail(request.getThumbnail())
                .pageNumbers(request.getPageNumbers())
                .publishingYear(request.getPublishingYear())
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .build();
        // Trả về book sau khi tạo
        return bookRepository.save(book);
    }

    public Book updateBook(Integer id, UpsertBookRequest request) {
        Book book = bookRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Not found book with id = " + id);
        });
        // Lấy ra ds category tương ứng (từ ds id gửi lên)
        Set<Category> categories = categoryRepository.findByIdIn(request.getCategoryIds());
        // Lấy ra ds category tương ứng (từ ds id gửi lên)
        Set<Author> authors = authorRepository.findByIdIn(request.getAuthorIds());

        book.setTitle(request.getTitle());
        book.setSlug(slugify.slugify(request.getTitle()));
        book.setDescription(request.getDescription());
        book.setCategories(categories);
        book.setAuthors(authors);
        book.setPrice(request.getPrice());

        book.setQuantity(request.getQuantity());

        book.setPageNumbers(request.getPageNumbers());
        book.setPublishingYear(request.getPublishingYear());

        return bookRepository.save(book);
    }
    // 5. Xóa book
    public void deleteBook(Integer id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Not found book with id = " + id);
        });
        String img=book.getThumbnail();
        if (img!=null&&img.contains("localhost:8080")){
            int index=img.lastIndexOf("/");
             String fileId= img.substring(index+1);
//            fileService.deleteFile(fileId-'0');
            deleteFile(id,Integer.parseInt(fileId));
        }
        bookRepository.delete(book);
    }

    //upload thumbnail
    public Book uploadThumbnail(Integer id, MultipartFile file) {
        Book book = bookRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Not found book with id = " + id);
        });
        String img=book.getThumbnail();
        if (img!=null&&img.contains("localhost:8080")){
            int index=img.lastIndexOf("/");
            String fileId= img.substring(index+1);
//            fileService.deleteFile(fileId-'0');
            deleteFile(id,Integer.parseInt(fileId));
        }
        String filePath = fileService.uploadThumbnail(book, file);
        book.setThumbnail(filePath);
        bookRepository.save(book);

        return book;
    }

    // Đọc file
    public byte[] readThumbnail(Integer id, Integer fileId) {
        Book book = bookRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Not found book with id = " + id);
        });
        return fileService.readThumbnail(fileId);
    }
    // Xóa file
    public void deleteFile(Integer id, Integer fileId) {
        Book book = bookRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Not found user with id = " + id);
        });
        fileService.deleteThumbnail(fileId);
    }
}
