package com.example.orderservice.model.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Positive;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class InventoryItem {
    private long id;
    private String productCode;
    private Integer availableQuantity;
}