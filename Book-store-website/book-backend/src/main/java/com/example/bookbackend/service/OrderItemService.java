package com.example.bookbackend.service;

import com.example.bookbackend.entity.OrderItem;
import com.example.bookbackend.exception.NotFoundException;
import com.example.bookbackend.repository.OrderItemRepository;
import com.example.bookbackend.request.UpsertOrderItemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {
    @Autowired
    private OrderItemRepository orderItemRepository;
    public List<OrderItem> getAllByUserIdStatusTrue(Integer userId) {
       return orderItemRepository.findByUser_IdAndStatusIsTrue(userId);
    }

    public OrderItem createOrderItem(UpsertOrderItemRequest request) {
        OrderItem newOrderItem=new OrderItem();
        newOrderItem.setBook(request.getBook());
        newOrderItem.setAmount(request.getAmount());
        newOrderItem.setUser(request.getUser());

       return orderItemRepository.save(newOrderItem);
    }

    public OrderItem updateOrderItem(Integer id, UpsertOrderItemRequest request) {
        OrderItem orderItem = orderItemRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Not found orderItem with id = " + id);
        });
        orderItem.setAmount(request.getAmount());
        return orderItemRepository.save(orderItem);
    }

    public void deleteOrderItem(Integer id) {
        OrderItem orderItem = orderItemRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Not found orderItem with id = " + id);
        });
        orderItemRepository.delete(orderItem);
    }
}
