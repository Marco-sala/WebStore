package com.webstore.webshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Employee {

    private String dni;
    private String name;
    private String lastNameFather;
    private String lastNameMother;
    private String password;
    private String rol;

}
