package com.example.bookbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "image_book")
public class ImageBook {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;


    @Column(name = "uploaded_at")
    private LocalDateTime uploadedAt;

    @Lob        //Large Object
    @Column(name = "data")
    private byte[] data;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;


    @PrePersist
    public void prePersist() {
        uploadedAt = LocalDateTime.now();
    }
}