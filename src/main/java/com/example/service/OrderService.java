package com.example.service;

import org.springframework.stereotype.Service;

@Service
public class OrderService {

    public String createOrder(Long userId, Long productId, Integer quantity) {
        return "Order created for user " + userId + ", product " + productId + ", quantity: " + quantity;
    }

    public String getOrderStatus(Long orderId) {
        return "Order " + orderId + " status: Processing";
    }

    public String cancelOrder(Long orderId) {
        return "Order " + orderId + " has been cancelled";
    }
}