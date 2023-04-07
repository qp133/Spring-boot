package com.example.bookbackend.controller;

import com.example.bookbackend.entity.OrderItem;
import com.example.bookbackend.request.UpsertOrderItemRequest;
import com.example.bookbackend.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class OrderItemController {
    @Autowired
    private OrderItemService orderItemService;

    @GetMapping("orderItems/{userId}")
    public List<OrderItem> getAllByUserIdStatusTrue(@PathVariable Integer userId) {
        return orderItemService.getAllByUserIdStatusTrue(userId);
    }

    @PostMapping("orderItems")
    public OrderItem createOrderItem(@RequestBody UpsertOrderItemRequest request) {
        return orderItemService.createOrderItem(request);
    }

    @PutMapping("orderItems/{id}")
    public OrderItem updateOrderItem(@RequestBody UpsertOrderItemRequest request, @PathVariable Integer id) {
        return orderItemService.updateOrderItem(id, request);
    }
    @DeleteMapping("orderItems/{id}")
    public void deleteOrderItem(@PathVariable Integer id) {
        orderItemService.deleteOrderItem(id);
    }
}
