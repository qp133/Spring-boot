package com.example.bookbackend.controller;

import com.example.bookbackend.entity.Order;
import com.example.bookbackend.request.UpsetOrderRequest;
import com.example.bookbackend.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("admin/orders")
    public List<Order> getOrder(){
       return orderService.getOrder();
    }
    @GetMapping("user/orders/{userId}")
    public List<Order> getHistoryOrder(@PathVariable Integer userId){
        return orderService.getHistoryOrder(userId);
    }
    @GetMapping("admin/orders/{orderId}")
    public Order getOrderById(@PathVariable Integer orderId){
        return orderService.getOrderById(orderId);
    }
    @PostMapping("user/orders")
    public Order creatOrder(@RequestBody UpsetOrderRequest request) {
        return orderService.createOrder(request);
    }
    @PutMapping("admin/orders/{id}")
    public Order confirmOrder(@PathVariable Integer id){
        return orderService.confirmOrder(id);
    }
}
