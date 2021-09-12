package com.example.orderservice.model.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.math.BigDecimal;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class OrderItems{
    @Id
    @Column(name="id",nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "orderitem_seq")
    @SequenceGenerator(initialValue = 1,name = "orderitem_seq")
    private long id;
    private String productCode;
    @Positive
    private int quantity;
    private BigDecimal productPrice;

   }
