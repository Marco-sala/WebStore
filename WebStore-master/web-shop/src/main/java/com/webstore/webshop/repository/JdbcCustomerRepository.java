package com.webstore.webshop.repository;

import com.webstore.webshop.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Repository
public class JdbcCustomerRepository implements CustomerRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void create(Customer customer) {
        final String sql = "insert into customer(dni, name, lastNameFather, lastNameMother, email, password, district, birthDate) values (?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql, customer.getDni(), customer.getName(), customer.getLastNameFather(), customer.getLastNameMother(), customer.getEmail(), customer.getPassword(), customer.getDistrict(), customer.getBirthDate());
    }

    @Override
    public void update(Customer customer) {
        final String sql = "update customer set name = ?, lastNameFather = ?, lastNameMother = ?, email = ?, password = ?, district = ?, birthDate = ? where dni = ?";
        jdbcTemplate.update(sql, customer.getName(), customer.getLastNameFather(), customer.getLastNameMother(), customer.getEmail(), customer.getPassword(), customer.getDistrict(), customer.getBirthDate(), customer.getDni());
    }

    @Override
    public void delete(String dni) {
        final String sql = "delete customer where dni = ?";
        jdbcTemplate.update(sql, dni);
    }

    @Override
    public List<Customer> findAll() {
        final String sql = "select * from customer";
        return jdbcTemplate.query(sql, JdbcCustomerRepository::CustomerRowMapper);
    }

    @Override
    public Customer findById(String dni) {
        final String sql = "select * from customer where dni = ?";
        return jdbcTemplate.queryForObject(sql,
                new Object[]{dni},
                JdbcCustomerRepository::CustomerRowMapper);
    }

    public Customer login(String email, String password) {
        final String sql = "select * from customer where email = ? and password = ?";
        return jdbcTemplate.queryForObject(sql,
                new Object[]{email,password},
                JdbcCustomerRepository::CustomerRowMapper);
    }

    private static Customer CustomerRowMapper(ResultSet resultSet, int i)
            throws SQLException {
        String dni = resultSet.getString("dni");
        String name = resultSet.getString("name");
        String lastNameFather = resultSet.getString("lastNameFather");
        String lastNameMother = resultSet.getString("lastNameMother");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        String district = resultSet.getString("district");
        LocalDate birthDate = LocalDate.parse(resultSet.getString("birthDate"));
        return new Customer(dni, name, lastNameFather, lastNameMother, email, password, district, birthDate);
    }

}
