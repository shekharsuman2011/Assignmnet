package com.example.finalcasestudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class FinalCaseStudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinalCaseStudyApplication.class, args);
    }

}
