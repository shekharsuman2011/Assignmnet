package com.example.inventoryservice.model.persistence;

import com.example.inventoryservice.model.bean.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface InventoryDao extends JpaRepository<InventoryItem,Long> {
    Optional<InventoryItem> findInventoryItemByProductCode(String code);
}
