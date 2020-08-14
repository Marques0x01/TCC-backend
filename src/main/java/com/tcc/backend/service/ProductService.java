package com.tcc.backend.service;

import com.tcc.backend.dto.AddressProductRegisterDTO;
import com.tcc.backend.dto.ProductRegisterDTO;
import com.tcc.backend.dto.ProductWithoutObjDTO;
import com.tcc.backend.model.Address;
import com.tcc.backend.model.Image;
import com.tcc.backend.model.Product;
import com.tcc.backend.model.User;
import com.tcc.backend.repository.AddressRepository;
import com.tcc.backend.repository.ImageRepository;
import com.tcc.backend.repository.ProductRepository;
import com.tcc.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.Deflater;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;

    public ProductRegisterDTO saveProduct(ProductRegisterDTO product) throws IOException {
            Product productModel = ProductRegisterDTO.convertToModel(product);
            Address address = AddressProductRegisterDTO.convertToModel(product.getAddress());
            User user = userRepository.findById(product.getUserId()).orElse(null);
            productModel.setUser(user);
            productModel.setAddress(address);
            addressRepository.save(address);
            userRepository.save(user);

        return ProductRegisterDTO.convertToDto(productRepository.save(productModel));
    }

    public ProductWithoutObjDTO getById(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        return ProductWithoutObjDTO.convertToDto(product);
    }



    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
        return outputStream.toByteArray();
    }
}
