package com.example.orderservice.model.dao;

import com.example.orderservice.model.bean.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemsDao extends JpaRepository<OrderItems,Long> {
}
