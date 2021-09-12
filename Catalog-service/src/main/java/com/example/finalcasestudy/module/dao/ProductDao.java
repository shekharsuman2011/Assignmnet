package com.example.finalcasestudy.module.dao;

import com.example.finalcasestudy.module.pojo.Prodcut;

import java.util.List;
import java.util.Optional;

public interface ProductDao {
    List<Prodcut> listAllProducts();
    Optional<Prodcut> getProductByCode(String code);
    List<Prodcut> listAllProductsInCategory(String category);
    List<String> categories();

}
