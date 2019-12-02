package com.webstore.webshop.repository;

import com.webstore.webshop.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcEmployeeRepository implements EmployeeRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void create(Employee employee) {
        final String sql = "insert into employee(dni, name, lastNameFather, lastNameMother, password, rol) values (?,?,?,?,?,?)";
        jdbcTemplate.update(sql, employee.getDni(), employee.getName(), employee.getLastNameFather(), employee.getLastNameMother(), employee.getPassword(), employee.getRol());
    }

    @Override
    public void update(Employee employee) {
        final String sql = "update employee set name = ?, lastNameFather = ?, lastNameMother = ?, password = ?, rol = ? where dni = ?";
        jdbcTemplate.update(sql, employee.getName(), employee.getLastNameFather(), employee.getLastNameMother(), employee.getPassword(), employee.getRol(), employee.getDni());
    }

    @Override
    public void delete(String dni) {
        final String sql = "delete employee where dni = ?";
        jdbcTemplate.update(sql, dni);
    }

    @Override
    public List<Employee> findAll() {
        final String sql = "select * from employee";
        return jdbcTemplate.query(sql, JdbcEmployeeRepository::EmployeeRowMapper);
    }

    @Override
    public Employee findById(String dni) {
        final String sql = "select * from employee where dni = ?";
        return jdbcTemplate.queryForObject(sql,
                new Object[]{dni},
                JdbcEmployeeRepository::EmployeeRowMapper);
    }

    public Employee login(String dni, String password) {
        final String sql = "select * from employee where dni = ? and password = ?";
        return jdbcTemplate.queryForObject(sql,
                new Object[]{dni,password},
                JdbcEmployeeRepository::EmployeeRowMapper);
    }

    private static Employee EmployeeRowMapper(ResultSet resultSet, int i)
            throws SQLException {
        String dni = resultSet.getString("dni");
        String name = resultSet.getString("name");
        String lastNameFather = resultSet.getString("lastNameFather");
        String lastNameMother = resultSet.getString("lastNameMother");
        String password = resultSet.getString("password");
        String rol = resultSet.getString("rol");
        return new Employee(dni, name, lastNameFather, lastNameMother, password, rol);
    }
}
