package com.example.controller;

import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<String> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserInfo(id));
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestParam String name) {
        return ResponseEntity.ok(userService.createUser(name));
    }

    @GetMapping
    public ResponseEntity<String> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
}