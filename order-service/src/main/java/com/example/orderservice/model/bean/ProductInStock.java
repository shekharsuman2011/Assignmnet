package com.example.orderservice.model.bean;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductInStock {
    private long id;
    private String productCode,name,description;
    private double price;
    private Integer availableQuantity;

    public ProductInStock(long id, String productCode, String name, String description, double price) {
        this.id = id;
        this.productCode = productCode;
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
