package com.webstore.webshop.service;

import com.webstore.webshop.model.Customer;
import com.webstore.webshop.repository.JdbcCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private JdbcCustomerRepository jdbcCustomerRepository;

    public void saveCustomer(Customer customer){
        jdbcCustomerRepository.create(customer);
    }

    public void updateCustomer(Customer customer){

        Customer currentCustomer = getCustomer(customer.getDni());
        if(currentCustomer != null){
            jdbcCustomerRepository.update(customer);
        }

    }

    public void deleteCustomer(String dni) {
        jdbcCustomerRepository.delete(dni);
    }

    public List<Customer> getAll() {
        return jdbcCustomerRepository.findAll();
    }

    public Customer getCustomer(String dni) {
        return jdbcCustomerRepository.findById(dni);
    }

    public Customer loginCustomer(String email, String password){
        return jdbcCustomerRepository.login(email,password);
    }

}
