package com.tcc.backend.controller;

import com.tcc.backend.dto.AddressUserViewDTO;
import com.tcc.backend.model.Address;
import com.tcc.backend.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;


    @PutMapping
    public AddressUserViewDTO updateUserAddress(@RequestBody AddressUserViewDTO dto){
        Address address = AddressUserViewDTO.convertToModel(dto);
        address = addressService.updateUserAddress(address, dto.getUserId());
        return AddressUserViewDTO.convertToDto(address);
    }
}
