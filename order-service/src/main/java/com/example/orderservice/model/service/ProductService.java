package com.example.orderservice.model.service;

import com.example.orderservice.model.bean.Product;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService  {

    @Autowired
    RestTemplate restTemplate;

    final String productURL="http://localhost:8084/products/";


    public List<Product> getAllProducts() {
        return Arrays.asList(restTemplate.getForObject(productURL,Product[].class));
    }

    @CircuitBreaker(name="product",fallbackMethod = "productDetailsFallBack")
    public Optional<Product> getProduct(String productCode) {
        return Optional.ofNullable(restTemplate.getForObject(productURL+"code/"+productCode,Product.class));
    }
    public Optional<Product> productDetailsFallBack(Exception e){
        Product p=new Product(0,null,null,null,null,0.0);
        return Optional.of(p);
    }
}
