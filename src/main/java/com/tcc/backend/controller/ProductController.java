package com.tcc.backend.controller;

import com.tcc.backend.dto.ProductRegisterDTO;
import com.tcc.backend.dto.ProductWithoutObjDTO;
import com.tcc.backend.model.Image;
import com.tcc.backend.repository.ImageRepository;
import com.tcc.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ImageRepository imageRepository;

    @PostMapping
    public ProductRegisterDTO save(@RequestBody ProductRegisterDTO product) throws IOException {
        return productService.saveProduct(product);
    }

//    @GetMapping("/{id}")
//    public Image getProduct(@PathVariable Long id){
//        final Optional<Image> retrievedImage = imageRepository.findById(id);
//        Image img = new Image(retrievedImage.get().getName(), retrievedImage.get().getType(),
//                decompressBytes(retrievedImage.get().getPicByte()));
//        return img;
//    }

    @GetMapping("/{id}")
    public ProductWithoutObjDTO getById(@PathVariable Long id){
        return productService.getById(id);
    }

    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe) {
        } catch (DataFormatException e) {
        }
        return outputStream.toByteArray();
    }
}
