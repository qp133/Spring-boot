package com.qp.blogapp;

import com.github.javafaker.Faker;
import com.github.slugify.Slugify;
import com.qp.blogapp.entity.Blog;
import com.qp.blogapp.entity.Category;
import com.qp.blogapp.entity.Comment;
import com.qp.blogapp.entity.User;
import com.qp.blogapp.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@SpringBootTest
public class InitDataTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private Faker faker;

    @Autowired
    private Slugify slugify;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void save_user() {
        for (int i = 0; i < 3; i++) {
            User user = User.builder()
                    .name(faker.name().fullName())
                    .email(faker.internet().emailAddress())
                    .password(passwordEncoder.encode("111"))
                    .roles(List.of("USER", "ADMIN"))
                    .build();
            userRepository.save(user);
        }
        for (int i = 0; i < 10; i++) {
            User user = User.builder()
                    .name(faker.name().fullName())
                    .email(faker.internet().emailAddress())
                    .password(passwordEncoder.encode("111"))
                    .roles(List.of("USER"))
                    .build();
            userRepository.save(user);
        }
    }

    @Test
    void save_category() {
        for (int i = 0; i < 5; i++) {
            Category category = Category.builder()
                    .name(faker.leagueOfLegends().champion())
                    .build();
            categoryRepository.save(category);
        }
    }

    @Test
    void save_blog() {
        Random rd = new Random();

        List<User> users = userRepository.findAll();
        List<User> userHasRoleAdmin = users.stream().filter(user -> user.getRoles().contains("ADMIN")).toList();
        List<Category> categories = categoryRepository.findAll();

        for (int i = 0; i < 30; i++) {
            //Random 1 user
            User rdUser = userHasRoleAdmin.get(rd.nextInt(userHasRoleAdmin.size()));

            //Random 1 category
            Set<Category> rdListCategory = new HashSet<>();
            for (int j = 0; j < 3; j++) {
                Category rdCategory = categories.get(rd.nextInt(categories.size()));
                rdListCategory.add(rdCategory);
            }

            //Tạo blog
            String title = faker.lorem().sentence(10);
            Blog blog = Blog.builder()
                    .title(title)
                    .slug(slugify.slugify(title))
                    .description(faker.lorem().sentence(30))
                    .content(faker.lorem().sentence(100))
                    .status(rd.nextInt(2) == 1)
                    .user(rdUser)
                    .categories(rdListCategory)
                    .build();
            blogRepository.save(blog);
        }
    }

    @Test
    void save_comment() {
        Random rd = new Random();

        List<User> users = userRepository.findAll();
        List<Blog> blogs = blogRepository.findAll();

        for (int i = 0; i < 100; i++) {
            //Random 1 user
            User rdUser = users.get(rd.nextInt(users.size()));

            //Random 1 blog
            Blog rdBlog = blogs.get(rd.nextInt(blogs.size()));

            Comment comment = Comment.builder()
                    .content(faker.lorem().sentence(10))
                    .user(rdUser)
                    .blog(rdBlog)
                    .build();
            commentRepository.save(comment);
        }
    }

}
