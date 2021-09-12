package com.example.finalcasestudy.controllers;

import com.example.finalcasestudy.module.pojo.Prodcut;
import com.example.finalcasestudy.module.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@ComponentScan(basePackages = "com.example.finalcasestudy")
public class ProductContorller {
    @Autowired
    ProductService productService;

    @CrossOrigin
    @RequestMapping(path="/products",method = RequestMethod.GET,produces = "Application/json")
    List<Prodcut> getAllProducts(){
        return productService.listAllProducts();
    }

    @CrossOrigin
    @RequestMapping(path = "/products/code/{code}",method = RequestMethod.GET,produces = "Application/json")
    Optional<Prodcut> getProduct(@PathVariable("code") String code){
        try {
            return productService.getProductByCode(code);
        }
        catch (EmptyResultDataAccessException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"data with that id does not exist in ou database");
        }
    }

    @CrossOrigin
    @RequestMapping(path="/products/{category}",method=RequestMethod.GET,produces = "Application/json")
    List<Prodcut> listAllProductsInCategory(@PathVariable("category") String category){
        return productService.listAllProductsInCategory(category);
    }

    @CrossOrigin
    @RequestMapping(path="/categories",method = RequestMethod.GET,produces = "Application/json")
    List<String> categories(){
        return productService.categories();
    }


}
