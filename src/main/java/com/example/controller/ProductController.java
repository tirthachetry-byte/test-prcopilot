package com.example.controller;

import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<String> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductDetails(id));
    }

    @PostMapping
    public ResponseEntity<String> addProduct(@RequestParam String name, @RequestParam Double price) {
        return ResponseEntity.ok(productService.addProduct(name, price));
    }

    @GetMapping
    public ResponseEntity<String> listProducts() {
        return ResponseEntity.ok(productService.listAllProducts());
    }
}