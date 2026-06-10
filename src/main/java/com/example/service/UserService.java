package com.example.service;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    public String getUserInfo(Long id) {
        return "User with ID: " + id;
    }

    public String createUser(String name) {
        return "User created with name: " + name;
    }

    public String getAllUsers() {
        return "Fetching all users from database";
    }
}