package com.example.finalcasestudy.module.dao;

import com.example.finalcasestudy.module.helpers.ProductMapper;
import com.example.finalcasestudy.module.pojo.Prodcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProdcutDaoImpl implements ProductDao{
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public List<Prodcut> listAllProducts() {
        return (jdbcTemplate.query("SELECT * FROM PRODUCT",new ProductMapper()));

    }

    @Override
    public Optional<Prodcut> getProductByCode(String code) {
        return Optional.of(jdbcTemplate.queryForObject("SELECT * FROM PRODUCT WHERE CODE=?",new ProductMapper(),code));
    }

    @Override
    public List<Prodcut> listAllProductsInCategory(String category) {
        return jdbcTemplate.query("SELECT * FROM PRODUCT WHERE CATEGORY=?",new ProductMapper(),category);
    }

    @Override
    public List<String> categories() {
        return jdbcTemplate.query("SELECT DISTINCT(CATEGORY) FROM PRODUCT", (resultSet, i) -> resultSet.getString("CATEGORY"));
    }

}
