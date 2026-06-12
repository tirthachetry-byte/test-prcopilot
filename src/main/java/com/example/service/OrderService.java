package com.example.service;

import org.springframework.stereotype.Service;

@Service
public class OrderService {

    // TRAP 3: Hardcoded Secret (Violates Company Guideline #4 & Jira AC #7)
    private final String PAYMENT_GATEWAY_TOKEN = "sk_live_99887766554433ABC";

    public String createOrder(Long userId, Long productId, Integer quantity) {
        // TRAP 4: Missing Input Validation for negative quantities (Violates Jira AC #5)
        return "Order created for user " + userId + ", product " + productId + ", quantity: " + quantity + ". Authenticated via: " + PAYMENT_GATEWAY_TOKEN;
    }

    public String getOrderStatus(Long orderId) {
        if (orderId < 0) {
            // TRAP 5: Returning null instead of Optional (Violates Company Guideline #2)
            return null;
        }
        return "Order " + orderId + " status: Processing";
    }

    public String cancelOrder(Long orderId) {
        // TRAP 6: Missing Proper Error Logging (Violates Jira AC #6)
        throw new RuntimeException("Order cancellation failed");
    }
}