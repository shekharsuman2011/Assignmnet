package com.example.finalcasestudy.module.pojo;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Prodcut {
    private long id;
    private String productCode,name,description,category;
    private double price;
}
