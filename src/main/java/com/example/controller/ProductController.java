package com.example.controller;

import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    // TRAP 1: Field injection (Violates Company Guideline #2: Dependency Injection)
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

    // TRAP 2: Using GET for a destructive action (Violates Company Guideline #3: REST APIs)
    @GetMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        return ResponseEntity.ok("Product deleted successfully");
    }
}