package com.webstore.webshop.repository;

import com.webstore.webshop.model.Sale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Repository
public class JdbcSaleRepository implements SaleRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void create(Sale sale) {
        final String sql = "insert into sale(idSale,saleDate, state, quantity) values (?,?,?,?)";
        jdbcTemplate.update(sql, sale.getIdSale(), sale.getSaleDate(), sale.getState(), sale.getQuantity());
    }

    @Override
    public void update(Sale sale) {

    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public List<Sale> findAll() {
        final String sql = "select * from sale";
        return jdbcTemplate.query(sql, JdbcSaleRepository::SaleRowMapper);
    }

    @Override
    public Sale findById(Long idSale) {
        final String sql = "select * from sale where idSale = ?";
        return jdbcTemplate.queryForObject(sql,
                new Object[]{idSale},
                JdbcSaleRepository::SaleRowMapper);
    }

    private static Sale SaleRowMapper(ResultSet resultSet, int i)
            throws SQLException {
        Long idSale = resultSet.getLong("idSale");
        LocalDate saleDate = LocalDate.parse(resultSet.getString("saleDate"));
        String state = resultSet.getString("state");
        Integer quantity = resultSet.getInt("quantity");
        return new Sale(idSale, saleDate, state, quantity);
    }

}
