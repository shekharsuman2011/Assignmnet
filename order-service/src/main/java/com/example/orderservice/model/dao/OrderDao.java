package com.example.orderservice.model.dao;

import com.example.orderservice.model.bean.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDao extends JpaRepository<Orders,Long> {
    List<Orders> getAllByCustomerEmail(String email );

}
