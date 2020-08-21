package com.tcc.backend.service;

import com.tcc.backend.controller.ImageController;
import com.tcc.backend.dto.ProductWithoutObjDTO;
import com.tcc.backend.model.Image;
import com.tcc.backend.model.Product;
import com.tcc.backend.repository.ImageRepository;
import com.tcc.backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.zip.Deflater;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;


    public Image saveImage(MultipartFile file, Long productId) throws IOException {
        Image image = new Image(file.getName(), file.getContentType(), Image.compressBytes(file.getBytes()));
        Product product = productService.getById(productId);
        image.setProduct(product);
        imageRepository.save(image);
        return image;
    }

    public Image getImageDecompressed(Long id) {
        final Optional<Image> retrievedImage = imageRepository.findById(id);
        Image img = new Image(retrievedImage.get().getName(), retrievedImage.get().getType(),
                Image.decompressBytes(retrievedImage.get().getPicByte()));
        return img;
    }
}
