package com.tcc.backend.controller;

import com.tcc.backend.dto.ProductRegisterDTO;
import com.tcc.backend.model.Image;
import com.tcc.backend.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/image")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping
    public Image save(@RequestParam("file") MultipartFile file, @RequestParam String productId) throws IOException {
        return this.imageService.saveImage(file, Long.parseLong(productId));
    }
}
