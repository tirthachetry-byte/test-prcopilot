package com.example.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    public String getProductDetails(Long id) {
        if (id == null) {
            logger.error("Failed to get product details: ID is null");
            throw new IllegalArgumentException("Product ID must not be null");
        }
        logger.info("Fetching details for product ID: {}", id);
        return "Product details for ID: " + id;
    }

    public String getProductDetailsByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            logger.error("Failed to get product details: Name is null or empty");
            throw new IllegalArgumentException("Product name must not be null or empty");
        }
        logger.info("Fetching details for product name: {}", name);
        return "Product details for name: " + name;
    }

    public String addProduct(String name, Double price) {
        if (name == null || name.trim().isEmpty()) {
            logger.error("Failed to add product: Name is null or empty");
            throw new IllegalArgumentException("Product name must not be null or empty");
        }
        if (price == null || price <= 0) {
            logger.error("Failed to add product: Price must be positive. Provided: {}", price);
            throw new IllegalArgumentException("Product price must be positive");
        }
        logger.info("Adding new product: {} with price: ${}", name, price);
        return "Product '" + name + "' added with price: $" + price;
    }

    public String listAllProducts() {
        logger.info("Listing all products from inventory");
        return "Listing all products from inventory";
    }
}
