package com.qp.blogapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "blog")
public class Blog {
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

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "thumbnail")
    private String thumbnail;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "published_at")
    private LocalDateTime publishedAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "status")
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

//    @ManyToMany
//    @JoinTable(name = "blog_category",
//            joinColumns = @JoinColumn(name = "blog_id"),
//            inverseJoinColumns = @JoinColumn(name = "category_id"))
//    private Set<Category> categories = new LinkedHashSet<>();


    @OneToMany(mappedBy = "blog", orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Comment> comments = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "blog_category",
            joinColumns = @JoinColumn(name = "blog_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new LinkedHashSet<>();

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        if (status) {
            publishedAt = createdAt;
        }
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
        if (status) {
            publishedAt = updatedAt;
        } else {
            publishedAt = null;
        }
    }

    @PreRemove
    public void preRemove() {
        this.setCategories(null);
        this.setUser(null);
    }
}