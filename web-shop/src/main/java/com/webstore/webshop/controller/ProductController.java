package com.webstore.webshop.controller;

import com.webstore.webshop.model.Product;
import com.webstore.webshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    //Devolver listado de productos
    @GetMapping("/products")
    public String getProductList(Model model) {
        List<Product> products = productService.getAll();
        model.addAttribute("products", products);
        return "product";
    }

    //Ir a la página de product-add
    @GetMapping("/products/add")
    public String addProduct(Model model) {
        model.addAttribute("product", new Product());
        return "product-add";
    }

    //Ingresar nuevo producto
    @PostMapping("/product/save")
    public String saveProduct(Product product,
                               Model model) {
        productService.saveProduct(product);

        //Ir a la lista de productos
        List<Product> products = productService.getAll();
        model.addAttribute("products", products);
        return "product";
    }

}
