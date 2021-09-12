package com.example.orderservice.model.bean;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product {
    private long id;
    private String productCode,name,description,category;
    private double price;
}