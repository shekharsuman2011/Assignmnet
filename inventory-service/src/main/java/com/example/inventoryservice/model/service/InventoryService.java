package com.example.inventoryservice.model.service;

import com.example.inventoryservice.model.bean.InventoryItem;

import java.util.List;
import java.util.Optional;

public interface InventoryService {
    Optional<InventoryItem> findItemByProductCode(String code);
    List<InventoryItem> getAllItems();
    boolean postItem(InventoryItem inventoryItem);
}
