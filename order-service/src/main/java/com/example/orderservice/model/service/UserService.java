package com.example.orderservice.model.service;

import com.example.orderservice.model.bean.User;

import java.util.Optional;

public interface UserService {
    Optional<User> getUser(String userEmail);
    boolean validateUser(String userEmail,User user);
}
