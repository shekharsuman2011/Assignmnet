package com.example.orderservice.model.service;

import com.example.orderservice.model.bean.InventoryItem;
import com.example.orderservice.model.bean.OrderItems;
import com.example.orderservice.model.bean.Orders;
import com.example.orderservice.model.bean.Product;
import com.example.orderservice.model.dao.OrderDao;
import com.example.orderservice.model.dao.OrderItemsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderDao orderDao;
    @Autowired
    OrderItemsDao orderItemsDao;
    @Autowired
    InventoryService inventoryService;
    @Autowired
    ProductService productService;
    @Override
    public Orders createOrder(Orders order) {
        try {

            order.getItems().stream().filter(e ->
                    {
                        if(e.getQuantity()>inventoryService.getInventoryItem(e.getProductCode()).get().
                                getAvailableQuantity()){
                            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST,"Quantity you provided is not available with us for product id"+e.getProductCode());
                        }
                        return true;
                    })
                    .forEach(e-> e.setProductPrice(BigDecimal.valueOf(productService.getProduct(e.getProductCode()).get().getPrice() * e.getQuantity())));

            return orderDao.save(order);
        }
        catch (HttpClientErrorException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"We dont have that item");
        }

    }

    @Override
    public Optional<Orders> findOrderById(Long id) {
        return orderDao.findById(id);
    }

    @Override
    public List<Orders> getAllByCustomerEmail(String email) {
        return orderDao.getAllByCustomerEmail(email);
    }
}
