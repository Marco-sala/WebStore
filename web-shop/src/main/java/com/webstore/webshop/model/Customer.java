package com.webstore.webshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Customer {

    private String dni;
    private String name;
    private String lastNameFather;
    private String lastNameMother;
    private String email;
    private String password;
    private String district;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

}
