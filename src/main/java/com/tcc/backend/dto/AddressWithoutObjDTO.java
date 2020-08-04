package com.tcc.backend.dto;

import com.tcc.backend.model.Address;
import com.tcc.backend.model.Product;
import com.tcc.backend.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@Builder
public class AddressWithoutObjDTO {

    private Long id;
    private String zipCode;
    private String district;
    private String city;
    private String state;
    private String country;
    private List<Long> userIds;
    private List<Long> productIds;


    public static AddressWithoutObjDTO from(Address address){
        if(address == null){
            return null;
        }

        return AddressWithoutObjDTO.builder()
                .id(address.getId())
                .zipCode(address.getZipCode())
                .district(address.getDistrict())
                .city(address.getCity())
                .state(address.getState())
                .country(address.getCountry())
                .userIds(address.getUser().stream().map(User::getId).collect(Collectors.toList()))
                .productIds(address.getProducts().stream().map(Product::getId).collect(Collectors.toList()))
                .build();
    }

    public static Address to(AddressWithoutObjDTO addressWithoutObjDTO){
        if(addressWithoutObjDTO == null){
            return null;
        }

        return Address.builder()
                .id(addressWithoutObjDTO.getId())
                .zipCode(addressWithoutObjDTO.getZipCode())
                .district(addressWithoutObjDTO.getDistrict())
                .city(addressWithoutObjDTO.getCity())
                .state(addressWithoutObjDTO.getState())
                .country(addressWithoutObjDTO.getCountry())
                .build();
    }
}
