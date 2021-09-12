package com.example.orderservice.model.dao;

import com.example.orderservice.model.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDao extends JpaRepository<User,String> {
    Optional<User> findUserByUserEmail(String userEmail);
}
