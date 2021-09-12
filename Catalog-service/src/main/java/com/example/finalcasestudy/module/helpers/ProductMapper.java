package com.example.finalcasestudy.module.helpers;

import com.example.finalcasestudy.module.pojo.Prodcut;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper implements RowMapper<Prodcut> {
    @Override
    public Prodcut mapRow(ResultSet resultSet, int i) throws SQLException {
        Prodcut prodcut=new Prodcut();
        prodcut.setProductCode(resultSet.getString("CODE"));
        prodcut.setId(resultSet.getLong("ID"));
        prodcut.setDescription(resultSet.getString("DESCRIPTION"));
        prodcut.setName(resultSet.getString("NAME"));
        prodcut.setPrice(resultSet.getLong("PRICE"));
        prodcut.setCategory(resultSet.getString("CATEGORY"));
        return  prodcut;
    }
}
