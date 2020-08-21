package com.tcc.backend.controller;

import com.tcc.backend.model.Image;
import com.tcc.backend.repository.ImageRepository;
import com.tcc.backend.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/image")
public class ImageController {

    @Autowired
    private ImageService imageService;
    @Autowired
    private ImageRepository imageRepository;

    @PostMapping
    public Image save(@RequestParam("file") MultipartFile file, @RequestParam String productId) throws IOException {
        return this.imageService.saveImage(file, Long.parseLong(productId));
    }

    @GetMapping("/{id}")
    public Image getProduct(@PathVariable Long id){
        return imageService.getImageDecompressed(id);
    }


}
