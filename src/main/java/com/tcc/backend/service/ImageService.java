package com.tcc.backend.service;

import com.tcc.backend.controller.ImageController;
import com.tcc.backend.dto.ProductWithoutObjDTO;
import com.tcc.backend.model.Image;
import com.tcc.backend.model.Product;
import com.tcc.backend.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private ProductService productService;


    public Image saveImage(MultipartFile file, Long productId) throws IOException {
        Image image = new Image(file.getName(), file.getContentType(), compressBytes(file.getBytes()));
        Product product = ProductWithoutObjDTO.convertToModel(productService.getById(productId));
        image.setProduct(product);
        imageRepository.save(image);
        return image;
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
