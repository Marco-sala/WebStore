package com.webstore.webshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Sale {

    private Long idSale;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate saleDate;
    private String state;
    private Integer quantity;

}
