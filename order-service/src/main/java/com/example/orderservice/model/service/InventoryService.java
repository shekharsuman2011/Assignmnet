package com.example.orderservice.model.service;

import com.example.orderservice.model.bean.InventoryItem;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {

    final String inventoryURL="http://localhost:8083/inventory/";
    @Autowired
    RestTemplate restTemplate;


    public List<InventoryItem> inventoryList() {
        return Arrays.asList(restTemplate.getForObject(inventoryURL,InventoryItem[].class));
    }
    @CircuitBreaker(name="inventory",fallbackMethod = "inventoryItemFallBack")
    public Optional<InventoryItem> getInventoryItem(String productCode) {
        return Optional.ofNullable(restTemplate.getForObject(inventoryURL+productCode,InventoryItem.class));
    }
    public Optional<InventoryItem> inventoryItemFallBack(Exception e){
        return Optional.of(new InventoryItem(0,"dummy product",0));
    }
}
