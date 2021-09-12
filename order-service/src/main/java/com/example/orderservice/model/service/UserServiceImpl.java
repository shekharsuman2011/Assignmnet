package com.example.orderservice.model.service;

import com.example.orderservice.model.bean.User;
import com.example.orderservice.model.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserDao userDao;
    @Override
    public Optional<User> getUser(String userEmail) {
        return userDao.findUserByUserEmail(userEmail);
    }

    @Override
    public boolean validateUser(String userEmail, User user) {
        User u=userDao.findUserByUserEmail(userEmail).get();
        return u.getUserPassword().equals(user.getUserPassword());

    }
}
