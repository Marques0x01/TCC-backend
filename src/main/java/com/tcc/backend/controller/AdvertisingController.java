package com.tcc.backend.controller;

import com.tcc.backend.model.Advertising;
import com.tcc.backend.repository.AdvertisingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/avertising")
public class AdvertisingController {

    @Autowired
    private AdvertisingRepository advertisingRepository;

    @PostMapping
    public Advertising save(Advertising advertising){
        advertisingRepository.save(advertising);
        return advertising;
    }
}
