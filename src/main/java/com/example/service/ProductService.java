package com.example.service;

import org.springframework.stereotype.Service;

@Service
public class ProductService {

    public String getProductDetails(Long id) {
        return "Product details for ID: " + id;
    }

    public String getProductDetailsByName(String name) {
        return "Product details for name: " + name;
    }

    public String addProduct(String name, Double price) {
        return "Product '" + name + "' added with price: $" + price;
    }

    public String listAllProducts() {
        return "Listing all products from inventory";
    }
}
