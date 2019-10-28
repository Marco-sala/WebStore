package com.webstore.webshop.service;

import com.webstore.webshop.model.Product;
import com.webstore.webshop.repository.JdbcProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private JdbcProductRepository jdbcProductRepository;

    public void saveProduct(Product product){
        jdbcProductRepository.create(product);
    }

    public void updateProduct(Product product){

        Product currentProduct = getProduct(product.getSku());
        if(currentProduct != null){
            jdbcProductRepository.update(product);
        }

    }

    public void deleteProduct(String sku) {
        jdbcProductRepository.delete(sku);
    }

    public List<Product> getAll() {
        return jdbcProductRepository.findAll();
    }

    public Product getProduct(String sku) {
        return jdbcProductRepository.findById(sku);
    }

}
