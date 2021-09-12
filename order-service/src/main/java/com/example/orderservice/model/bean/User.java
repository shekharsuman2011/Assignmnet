package com.example.orderservice.model.bean;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.context.annotation.Bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {
    @Id
    @Column(nullable = false)
    String userEmail;
    @Column(nullable = false)
    String name;
    @Column(nullable = false)
    String phoneNumber;
    @Column(nullable = false)
    String userPassword;
}
