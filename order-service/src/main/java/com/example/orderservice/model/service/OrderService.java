package com.example.orderservice.model.service;

import com.example.orderservice.model.bean.Orders;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Orders createOrder(Orders Order);
    Optional<Orders> findOrderById(Long id);
    List<Orders> getAllByCustomerEmail(String email );
}
