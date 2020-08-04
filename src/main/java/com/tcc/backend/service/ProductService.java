package com.tcc.backend.service;

import com.tcc.backend.repository.AddressRepository;
import com.tcc.backend.repository.ImageRepository;
import com.tcc.backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private AddressRepository addressRepository;

    public void saveProduct(){

    }
}
