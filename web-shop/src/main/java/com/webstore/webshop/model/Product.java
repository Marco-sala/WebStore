package com.webstore.webshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {

    private String sku;
    private String name;
    private String description;
    private Double price;
    private String imgProduct;
    private String category;

}
