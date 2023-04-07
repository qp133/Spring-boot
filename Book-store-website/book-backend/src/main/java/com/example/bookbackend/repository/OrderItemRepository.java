package com.example.bookbackend.repository;

import com.example.bookbackend.entity.Category;
import com.example.bookbackend.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
    List<OrderItem> findByUser_IdAndStatusIsTrue(Integer id);
    Set<OrderItem> findByIdIn(List<Integer> ids);
}