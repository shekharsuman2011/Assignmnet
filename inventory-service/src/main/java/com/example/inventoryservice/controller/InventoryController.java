package com.example.inventoryservice.controller;

import com.example.inventoryservice.model.bean.InventoryItem;
import com.example.inventoryservice.model.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@ComponentScan(basePackages = "com.example.inventoryservice")
public class InventoryController {
    @Autowired
    InventoryService inventoryService;

    @CrossOrigin
    @GetMapping(path = "/inventory",produces = "Application/json")
    List<InventoryItem> getAllItems(){
        return inventoryService.getAllItems();
    }

    @CrossOrigin
    @GetMapping(path= "/inventory/{code}",produces = "Application/json")
    Optional<InventoryItem> getItemByProdcutId(@PathVariable("code") String productCode){
        if(!inventoryService.findItemByProductCode(productCode).isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No matching id exists in our database");
        }
        else{
            ResponseEntity.status(HttpStatus.OK);
        }
        System.out.println(inventoryService.findItemByProductCode(productCode).get().toString());
        return  inventoryService.findItemByProductCode(productCode);
    }

    @CrossOrigin
    @PostMapping(path="/inventory/post",consumes = "Application/json",produces = "Application/json")
    boolean postItem(@Valid @RequestBody InventoryItem inventoryItem){
        return inventoryService.postItem(inventoryItem);
    }
}
