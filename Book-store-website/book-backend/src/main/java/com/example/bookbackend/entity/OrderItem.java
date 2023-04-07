package com.example.bookbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "order_item")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "amount")
    private Integer amount;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "status")
    private Boolean status;

    @ManyToMany(mappedBy = "orderItems")
    @JsonIgnore
    private Set<Order> orders = new LinkedHashSet<>();

    @PrePersist
    public void prePersist() {
        status=true;
    }

    @PreRemove
    public void preRemove() {
        for (Order order:orders){
            order.getOrderItems().remove(this);
        }
    this.setUser(null);
    this.setBook(null);
    }
}