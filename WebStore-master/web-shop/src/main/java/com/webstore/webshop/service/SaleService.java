package com.webstore.webshop.service;

import com.webstore.webshop.model.Sale;
import com.webstore.webshop.repository.JdbcSaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService {

    @Autowired
    private JdbcSaleRepository jdbcSaleRepository;

    public void saveSale(Sale sale){
        jdbcSaleRepository.create(sale);
    }

    public List<Sale> getAll() {
        return jdbcSaleRepository.findAll();
    }

    public Sale getSale(Long idSale) {
        return jdbcSaleRepository.findById(idSale);
    }

}
