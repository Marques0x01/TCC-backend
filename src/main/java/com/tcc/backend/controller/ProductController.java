package com.tcc.backend.controller;

import com.tcc.backend.dto.ProductBasicDataDTO;
import com.tcc.backend.dto.ProductViewDTO;
import com.tcc.backend.dto.ProductRegisterDTO;
import com.tcc.backend.dto.ProductWithoutObjDTO;
import com.tcc.backend.model.Product;
import com.tcc.backend.repository.ImageRepository;
import com.tcc.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ProductRegisterDTO save(@RequestBody ProductRegisterDTO product) throws IOException {
        return productService.saveProduct(product);
    }

    @GetMapping
    public List<ProductViewDTO> getAll(){
        List<Product> products = productService.getAll();
        return  products.stream().map(product -> ProductViewDTO.convertToDto(product)).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductBasicDataDTO getById(@PathVariable Long id){
        Product product = productService.getById(id);
        if(product == null){
            return null;
        }
        return ProductBasicDataDTO.convertToDto(product);
    }


}
