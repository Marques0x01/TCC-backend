package com.tcc.backend.controller;

import com.tcc.backend.model.Product;
import com.tcc.backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/avertising")
public class ProductController {

    @Autowired
    private ProductRepository advertisingRepository;

    @PostMapping
    public Product save(Product product){
        advertisingRepository.save(product);
        return product;
    }
}
