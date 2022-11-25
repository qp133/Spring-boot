package com.qp.testjpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="blog")
public class Blog {
    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator",
            strategy = "com.example.testapi.MyGenerator")
    @Column(name = "id", nullable = false)
    private String id;
    private String title;
}