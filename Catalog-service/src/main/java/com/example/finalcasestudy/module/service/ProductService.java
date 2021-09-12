package com.example.finalcasestudy.module.service;

import com.example.finalcasestudy.module.pojo.Prodcut;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    public List<Prodcut> listAllProducts();
    Optional<Prodcut> getProductByCode(String code);
    List<Prodcut> listAllProductsInCategory(String category);
    List<String> categories();

}
