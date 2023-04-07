package com.example.bookbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "book")

public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "slug")
    private String slug;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "thumbnail")
    private String thumbnail;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "page_numbers")
    private Integer pageNumbers;

    @Column(name = "publishing_year")
    private Integer publishingYear;

    @Column(name = "price")
    private Long price;

    @Column(name = "quantity")
    private Integer quantity;

    @OneToMany(mappedBy = "book" , cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Comment> comments = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "book_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "authors_id"))
    private Set<Author> authors = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "book_categories",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "categories_id"))
    private Set<Category> categories = new LinkedHashSet<>();

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<OrderItem> orderItems = new LinkedHashSet<>();

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

    @PreRemove
    private void removeBookFromCategoriesAndAuthors() {
        for (Category c : categories) {
            c.getBooks().remove(this);
        }
        for (Author a : authors) {
            a.getBooks().remove(this);
        }

    }
}