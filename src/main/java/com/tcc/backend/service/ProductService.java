package com.tcc.backend.service;

import com.tcc.backend.dto.AddressProductRegisterDTO;
import com.tcc.backend.dto.ProductRegisterDTO;
import com.tcc.backend.dto.ProductSearchDTO;
import com.tcc.backend.model.*;
import com.tcc.backend.repository.AddressRepository;
import com.tcc.backend.repository.ProductRepository;
import com.tcc.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;


    public ProductRegisterDTO saveProduct(ProductRegisterDTO product) throws IOException {
            Product productModel = ProductRegisterDTO.convertToModel(product);
            productModel.setStatus(ProductStatus.ACTIVE);
            Address address = AddressProductRegisterDTO.convertToModel(product.getAddress());
            User user = userRepository.findById(product.getUserId()).orElse(null);
            productModel.setUser(user);
            productModel.setAddress(address);
            productModel.setCreationDate(new Timestamp(System.currentTimeMillis()));
            addressRepository.save(address);
            userRepository.save(user);

        return ProductRegisterDTO.convertToDto(productRepository.save(productModel));
    }

    public Product getById(Long id) {
        return productRepository.findById(id).orElse(null);
    }


    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public List<Product> searchProduct(ProductSearchDTO productSearchDTO){
        if(productSearchDTO == null){
            return productRepository.findAll();
        }
        ProductSpecification spec = new ProductSpecification();
        if(productSearchDTO.getTitle() != null && productSearchDTO.getTitle().trim() != ""){
            spec.add(new SearchCriteria("title", ":", productSearchDTO.getTitle()));
        }
        if(productSearchDTO.getMaxPrice() != null){
            spec.add(new SearchCriteria("price", "<", productSearchDTO.getMaxPrice()));
        }
        if(productSearchDTO.getMinPrice() != null){
            spec.add(new SearchCriteria("price", ">", productSearchDTO.getMinPrice()));
        }
        if(productSearchDTO.getCategory() != null){
            spec.add(new SearchCriteria("category", ":", productSearchDTO.getCategory()));
        }
        if(productSearchDTO.getRentType() != null){
            spec.add(new SearchCriteria("rentType", ":", productSearchDTO.getRentType()));
        }
        if(productSearchDTO.getCity() != null){
            spec.add(new SearchCriteria("city", ":", productSearchDTO.getCity()));
        }
        if(productSearchDTO.getState() != null){
            spec.add(new SearchCriteria("state", ":", productSearchDTO.getState()));
        }

        return productRepository.findAll(Specification.where(spec));
    }
}
