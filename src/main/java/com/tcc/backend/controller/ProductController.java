package com.tcc.backend.controller;

import com.tcc.backend.dto.*;
import com.tcc.backend.model.Product;
import com.tcc.backend.dto.ProductSearchDTO;
import com.tcc.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

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


    @PostMapping("/search")
    public List<ProductSearchResultDTO> searchProduct(@RequestBody ProductSearchDTO productSearchDTO) {
        List<Product> product = productService.searchProduct(productSearchDTO);
        return product.stream().map(ProductSearchResultDTO::convertToDto).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
    }


}
