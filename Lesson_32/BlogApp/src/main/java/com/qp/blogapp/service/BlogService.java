package com.qp.blogapp.service;

import com.github.slugify.Slugify;
import com.qp.blogapp.entity.Blog;
import com.qp.blogapp.entity.Category;
import com.qp.blogapp.entity.User;
import com.qp.blogapp.exception.NotFoundException;
import com.qp.blogapp.repository.BlogRepository;
import com.qp.blogapp.repository.CategoryRepository;
import com.qp.blogapp.repository.UserRepository;
import com.qp.blogapp.request.UpsertBlogRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class BlogService {
    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Slugify slugify;

    public List<Blog> getBlogs() {
        return blogRepository.findAll();
    }

    public Blog getBlogById(Integer id) {
        return blogRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Not found blog with id = " + id);
        });
    }

    public Blog createBlog(UpsertBlogRequest request) {
        //Lấy ra danh sách category tương ứng từ danh sách id gửi lên
        Set<Category> categories = categoryRepository.findByIdIn(request.getCategoryIds());

        //Lấy ra ai là người tạo bài viết -> chính là user đang login
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        User user = userRepository.findByEmail(userEmail).orElseThrow(() -> {
            throw new NotFoundException("Not found user with email = " + userEmail);
        });

        //Tạo bài viết
        Blog blog = Blog.builder()
                .title(request.getTitle())
                .slug(slugify.slugify(request.getTitle()))
                .content(request.getContent())
                .description(request.getDescription())
                .thumbnail(request.getThumbnail())
                .status(request.getStatus())
                .user(user)
                .categories(categories)
                .build();

        //Trả về bài viết sau khi tạo
        return blogRepository.save(blog);
    }

    public Blog updateBlog(Integer id, UpsertBlogRequest request) {
        Blog blog = blogRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Not found blog with id = " + id);
        });

        //Lấy ra danh sách category tương ứng từ danh sách id gửi lên
        Set<Category> categories = categoryRepository.findByIdIn(request.getCategoryIds());

        //Tạo bài viết
        blog.setTitle(request.getTitle());
        blog.setSlug(slugify.slugify(request.getTitle()));
        blog.setContent(request.getContent());
        blog.setDescription(request.getDescription());
        blog.setThumbnail(request.getThumbnail());
        blog.setStatus(request.getStatus());
        blog.setCategories(categories);

        //Trả về bài viết sau khi tạo
        return blogRepository.save(blog);
    }

    public void deleteBlog(Integer id) {
        Blog blog = blogRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Not found blog with id = " + id);
        });
        blogRepository.delete(blog);
    }


}
