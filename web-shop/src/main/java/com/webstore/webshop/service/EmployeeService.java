package com.webstore.webshop.service;

import com.webstore.webshop.model.Employee;
import com.webstore.webshop.repository.JdbcEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private JdbcEmployeeRepository jdbcEmployeeRepository;

    public void saveEmployee(Employee employee){
        jdbcEmployeeRepository.create(employee);
    }

    public void updateEmployee(Employee employee){

        Employee currentEmployee = getEmployee(employee.getDni());
        if(currentEmployee != null){
            jdbcEmployeeRepository.update(employee);
        }

    }

    public void deleteEmployee(String dni) {
            jdbcEmployeeRepository.delete(dni);
    }

    public List<Employee> getAll() {
        return jdbcEmployeeRepository.findAll();
    }

    public Employee getEmployee(String dni) {
        return jdbcEmployeeRepository.findById(dni);
    }

    public Employee loginEmployee(String dni, String password){
        return jdbcEmployeeRepository.login(dni,password);
    }

}
