package com.webstore.webshop.repository;

import com.webstore.webshop.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcProductRepository implements ProductRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void create(Product product) {
        final String sql = "insert into product(sku, name, description, price, imgProduct, category) values (?,?,?,?,?,?)";
        jdbcTemplate.update(sql, product.getSku(), product.getName(), product.getDescription(), product.getPrice(), product.getImgProduct(), product.getCategory());
    }

    @Override
    public void update(Product product) {
        final String sql = "update product set name = ?, description = ?, price = ?, imgProduct = ?, category = ? where sku = ?";
        jdbcTemplate.update(sql, product.getName(), product.getDescription(), product.getPrice(), product.getImgProduct(), product.getCategory(), product.getSku());
    }

    @Override
    public void delete(String sku) {
        final String sql = "delete product where sku = ?";
        jdbcTemplate.update(sql, sku);
    }

    @Override
    public List<Product> findAll() {
        final String sql = "select * from product";
        return jdbcTemplate.query(sql, JdbcProductRepository::ProductRowMapper);
    }

    @Override
    public Product findById(String sku) {
        final String sql = "select * from product where sku = ?";
        return jdbcTemplate.queryForObject(sql,
                new Object[]{sku},
                JdbcProductRepository::ProductRowMapper);
    }

    private static Product ProductRowMapper(ResultSet resultSet, int i)
            throws SQLException {
        String sku = resultSet.getString("sku");
        String name = resultSet.getString("name");
        String description = resultSet.getString("description");
        Double price = resultSet.getDouble("price");
        String imgProduct = resultSet.getString("imgProduct");
        String category = resultSet.getString("category");
        return new Product(sku, name, description, price, imgProduct, category);
    }

}
