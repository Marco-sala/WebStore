package com.webstore.webshop.controller;

import com.webstore.webshop.model.Sale;
import com.webstore.webshop.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class SaleController {

    @Autowired
    private SaleService saleService;

    //Devolver listado de pedidos
    @GetMapping("/sales")
    public String getSaleList(Model model) {
        List<Sale> sales = saleService.getAll();
        model.addAttribute("sales", sales);
        return "sale";
    }

    //Ir a la página de sale-add
    @GetMapping("/sales/add")
    public String addSale(Model model) {
        model.addAttribute("sale", new Sale());
        return "sale-add";
    }

    //Ingresar nuevo pedido
    @PostMapping("/sale/save")
    public String saveSale(Sale sale,
                              Model model) {
        saleService.saveSale(sale);

        //Ir a la lista de pedidos
        List<Sale> sales = saleService.getAll();
        model.addAttribute("sales", sales);
        return "sale";
    }

}
