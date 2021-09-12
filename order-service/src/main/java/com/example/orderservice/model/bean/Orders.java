package com.example.orderservice.model.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Orders{
    @Id
    @Column(name="id",nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "order_seq")
    @SequenceGenerator(initialValue = 1,name = "order_seq")
    private long id;
    private String customerEmail;
    private String customerAddress;
    @Valid
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL , orphanRemoval = true)
    private List<OrderItems> items=new ArrayList<>();
    @Column(nullable = true)
    private int total;
    public Orders(String customerEmail, String customerAddress, List<OrderItems> items,int total) {
        this.customerEmail = customerEmail;
        this.customerAddress = customerAddress;
        this.items = items;
        this.total=total;
    }
}
