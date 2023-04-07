package com.example.bookbackend.service;

import com.example.bookbackend.entity.Author;
import com.example.bookbackend.entity.Order;
import com.example.bookbackend.entity.OrderItem;
import com.example.bookbackend.exception.BadRequestException;
import com.example.bookbackend.exception.NotFoundException;
import com.example.bookbackend.repository.OrderItemRepository;
import com.example.bookbackend.repository.OrderRepository;
import com.example.bookbackend.request.UpsetOrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;


    public Order createOrder(UpsetOrderRequest request) {
        Order order = new Order();
        if (request.getAddress().equals("")){
            throw new BadRequestException("Bạn cần nhập địa chỉ");
        }
        if (request.getOrderItemIds().size()==0){
            throw new BadRequestException("Cần tối thiểu 1 sản phẩm để đặt hàng");
        }
        order.setAddress(request.getAddress());
        Set<OrderItem> orderItems = orderItemRepository.findByIdIn(request.getOrderItemIds());
        order.setOrderItems(orderItems);
        return orderRepository.save(order);
    }

    public List<Order> getOrder() {
        return orderRepository.findByOrderByStatusAscCreateAtAsc();
    }

    public Order getOrderById(Integer orderId) {
        return orderRepository.findById(orderId).orElseThrow(()->{
            throw new NotFoundException("Not found order with id ="+orderId);
        });
    }

    public Order confirmOrder(Integer id) {
        Order order=orderRepository.findById(id).orElseThrow(()->{
            throw new NotFoundException("Not found order with id ="+id);
        });
        order.setStatus(true);
        return orderRepository.save(order);
    }

    public List<Order> getHistoryOrder(Integer userId) {
        List<Order> allOrders =orderRepository.findAll();
        List<Order> ordersByUser = new ArrayList<>();
        for (Order order : allOrders) {
            for (OrderItem orderItem : order.getOrderItems()) {
                if (orderItem.getUser().getId()
                        .equals(userId)) {
                    ordersByUser.add(order);
                    break;
                }
            }
            ordersByUser.sort(new Comparator<Order>() {
                @Override
                public int compare(Order o1, Order o2) {
                    return o2.getCreateAt().compareTo(o1.getCreateAt());
                }
            });
        }
        return ordersByUser;
    }
}
