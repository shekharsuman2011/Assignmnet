package com.example.finalcasestudy.module.service;

import com.example.finalcasestudy.module.dao.ProdcutDaoImpl;
import com.example.finalcasestudy.module.dao.ProductDao;
import com.example.finalcasestudy.module.pojo.Prodcut;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements  ProductService{
    @Autowired
    ProductDao productDao;


    @Override
    public List<Prodcut> listAllProducts() {
        return productDao.listAllProducts();
    }

    @Override
    public Optional<Prodcut> getProductByCode(String code) {
        return productDao.getProductByCode(code);
    }

    @Override
    public List<Prodcut> listAllProductsInCategory(String category) {
        return productDao.listAllProductsInCategory(category);
    }

    @Override
    public List<String> categories() {
        return productDao.categories();
    }



}
