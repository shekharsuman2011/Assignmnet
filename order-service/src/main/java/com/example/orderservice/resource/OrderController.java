package com.example.orderservice.resource;

import com.example.orderservice.model.bean.InventoryItem;
import com.example.orderservice.model.bean.Orders;
import com.example.orderservice.model.dao.OrderDao;
import com.example.orderservice.model.service.InventoryService;
import com.example.orderservice.model.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class OrderController {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderDao orderDao;
    @Autowired
    InventoryService inventoryService;

    @CrossOrigin
    @GetMapping(path="orders/id/{id}",produces = "Application/json")
    Optional<Orders> getOrderById(@PathVariable("id") Long id){
        if(!orderService.findOrderById(id).isPresent()){
            System.out.println("not found");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No matching id exists in our database");
        }
        else{
            ResponseEntity.status(HttpStatus.OK);
        }
        System.out.println(orderService.findOrderById(id).toString());
        return orderService.findOrderById(id);
    }

    @CrossOrigin
    @GetMapping(path="orders/email/{email}",produces = "Application/json")
    List<Orders> getOrderByEmail(@PathVariable("email") String  email){
        if(orderService.getAllByCustomerEmail(email).size()==0){
            System.out.println("not found");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No matching id exists in our database");
        }
        else{
            ResponseEntity.status(HttpStatus.OK);
        }
        System.out.println(orderService.getAllByCustomerEmail(email).toString());
        return orderService.getAllByCustomerEmail(email);
    }

    @CrossOrigin
    @PostMapping(path="orders/post",consumes = "Application/json",produces = "Application/json")
    Orders createOrder(@Valid @RequestBody   Orders order){
        return orderService.createOrder(order);
    }

    @CrossOrigin
    @GetMapping(path="inventory/{productId}" ,produces = "Application/json")
    InventoryItem inventoryItem(@PathVariable("productId") String productCode){
        return inventoryService.getInventoryItem(productCode).get();
    }

    @CrossOrigin
    @GetMapping(path="orders",produces = "Application/json")
    List<Orders> allOrder(){
        return orderDao.findAll();
    }

}
