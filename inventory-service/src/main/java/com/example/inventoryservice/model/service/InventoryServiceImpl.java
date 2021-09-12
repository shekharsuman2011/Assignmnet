package com.example.inventoryservice.model.service;

import com.example.inventoryservice.model.bean.InventoryItem;
import com.example.inventoryservice.model.persistence.InventoryDao;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryServiceImpl implements InventoryService{
    @Autowired
    InventoryDao inventoryDao;

    @Override
    public Optional<InventoryItem> findItemByProductCode(String code) {
        return inventoryDao.findInventoryItemByProductCode(code);
    }

    @Override
    public List<InventoryItem> getAllItems() {
        return inventoryDao.findAll();
    }

    @Override
    public boolean postItem(InventoryItem inventoryItem) {
        InventoryItem i=inventoryDao.save(inventoryItem);
        return i!=null;
    }

}
