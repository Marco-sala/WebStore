package com.webstore.webshop.controller;


import com.webstore.webshop.model.Customer;

import com.webstore.webshop.service.CustomerService;
import com.webstore.webshop.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    //Devolver listado de clientes
    @GetMapping("/customers")
    public String getCustomerList(Model model) {
        List<Customer> customers = customerService.getAll();
        model.addAttribute("customers", customers);
        return "customer";
    }

    //Ir a la página de customer-add
    @GetMapping("/customers/add")
    public String addCustomer(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer-add";
    }

    //Ingresar nuevo cliente
    @PostMapping("/customer/save")
    public String saveCustomer(Customer customer,
                               Model model) {
        customerService.saveCustomer(customer);

        //Ir a la lista de cliente
        List<Customer> customers = customerService.getAll();
        model.addAttribute("customers", customers);
        return "customer-save";
    }

    //Ir a la página de iniciar sesion
    @GetMapping("/customers/login")
    public String gotToLogin(Model model){

        model.addAttribute("customer", new Customer());

        return "customer-login";
    }

    //Iniciar sesión
    @PostMapping("/customers/login")
    public String loginCustomer(Customer customer,
                                Model model) {
        Customer currentCustomer = customerService.loginCustomer(customer.getEmail(), customer.getPassword());
        if(currentCustomer != null){
            return "product-list";
        } else {
            return "error";
        }

    }

    //Ir a la página de actualizar cliente
    @GetMapping("/customer/edit/{dni}")
    public String getCustomerForUpdate(@PathVariable String dni,
                                       Model model) {

        Customer currentCustomer = customerService.getCustomer(dni);
        model.addAttribute("Customer", currentCustomer);
        return "Customer-edit";
    }

    //Actualizar clientes
    @PostMapping("/Customers/update/{dni}")
    public String updateCustomer(@PathVariable String dni,
                                 Customer customer,
                                 Model model) {

        //Update
        customerService.updateCustomer(customer);

        //list
        List<Customer> customers = customerService.getAll();
        model.addAttribute("Customers", customer);
        return "Customer";
    }

    //Eliminar empleado
    @GetMapping("/Customers/delete/{dni}")
    public String deleteCustomer(@PathVariable String dni,
                                 Model model) {
        //Delete
        customerService.deleteCustomer(dni);

        //Ir a la lista de empleados
        List<Customer> customers = customerService.getAll();
        model.addAttribute("Customers", customers);
        return "Customer";
    }
}
