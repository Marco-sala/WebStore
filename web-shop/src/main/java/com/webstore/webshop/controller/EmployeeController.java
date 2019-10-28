package com.webstore.webshop.controller;

import com.webstore.webshop.model.Employee;
import com.webstore.webshop.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    //Devolver listado de empleados
    @GetMapping("/employees")
    public String getEmployeeList(Model model) {
        List<Employee> employees = employeeService.getAll();
        model.addAttribute("employees", employees);
        return "employee";
    }

    //Ir a la página de employee-add
    @GetMapping("/employees/add")
    public String addEmployee(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee-add";
    }

    //Ingresar nuevo empleado
    @PostMapping("/employee/save")
    public String saveEmployee(Employee employee,
                               Model model) {
        employeeService.saveEmployee(employee);

        //Ir a la lista de empleados
        List<Employee> employees = employeeService.getAll();
        model.addAttribute("employees", employees);
        return "employee";
    }

    //Ir a la página de loginCorporativo
    @GetMapping("/employees/login")
    public String gotToLogin(Model model){

        model.addAttribute("employee", new Employee());

        return "employee-login";
    }

    //Iniciar sesión en loginCorporativo
    @PostMapping("/employees/login")
    public String loginEmployee(Employee employee,
                                Model model) {
        Employee currentEmployee = employeeService.loginEmployee(employee.getDni(), employee.getPassword());
        if(currentEmployee != null){
            return "maintenance";
        } else {
            return "employee-login";
        }

    }

    //Ir a la página de actualizar empleado
    @GetMapping("/employees/edit/{dni}")
    public String getEmployeeForUpdate(@PathVariable String dni,
                                      Model model) {

        Employee currentEmployee = employeeService.getEmployee(dni);
        model.addAttribute("employee", currentEmployee);
        return "employee-edit";
    }

    //Actualizar empleado
    @PostMapping("/employees/update/{dni}")
    public String updateEmployee(@PathVariable String dni,
                                Employee employee,
                                Model model) {

        //Update
        employeeService.updateEmployee(employee);

        //list
        List<Employee> employees = employeeService.getAll();
        model.addAttribute("employees", employees);
        return "employee";
    }

    //Eliminar empleado
    @GetMapping("/employees/delete/{dni}")
    public String deleteEmployee(@PathVariable String dni,
                                Model model) {
        //Delete
        employeeService.deleteEmployee(dni);

        //Ir a la lista de empleados
        List<Employee> employees = employeeService.getAll();
        model.addAttribute("employees", employees);
        return "employee";
    }

}
