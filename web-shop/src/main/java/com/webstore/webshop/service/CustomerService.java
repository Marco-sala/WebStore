package com.webstore.webshop.service;

import com.webstore.webshop.model.Customer;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CustomerService {

    Customer customer1 =  new Customer("84512547", "Hans", "Velazco","Olaya","hans99@gmail.com","12345","Villa El Salvador", LocalDate.of(1999,1,8));

    List<Customer> customers = new ArrayList<>(
            Arrays.asList(
                    customer1
            )
    );

    public void register(Customer customer){
        customers.add(customer);
    }
    public Customer login(String email, String password){

        return customers.stream()
                .filter(c -> email.equalsIgnoreCase(c.getEmail()) && password.equalsIgnoreCase(c.getPassword()))
                .findFirst()
                .orElseGet(null);
    }

    public void update(Customer customer){
        Customer currentCustomer = findById(customer.getDni());
        if(currentCustomer != null){
            int index = customers.indexOf(currentCustomer);
            customer.setDni(currentCustomer.getDni());
            customers.set(index, customer);
        }
    }

    private Customer findById(String dni) {
        return customers.stream()
                .filter(c -> dni.equals(c.getDni()))
                .findFirst()
                .orElseGet(null);
    }

}
