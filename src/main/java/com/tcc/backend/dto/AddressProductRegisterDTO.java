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
public class AddressProductRegisterDTO {
    private Long id;
    private String zipCode;
    private String street;
    private String district;
    private String city;
    private String state;
    private String country;
    private Long userId;

    public static AddressProductRegisterDTO convertToDto(Address address){
        if(address == null){
            return null;
        }

        return AddressProductRegisterDTO.builder()
                .id(address.getId())
                .zipCode(address.getZipCode())
                .district(address.getDistrict())
                .city(address.getCity())
                .state(address.getState())
                .country(address.getCountry())
                .userId(address.getUser() != null ? address.getUser().getId() : null)
                .build();
    }

    public static Address convertToModel(AddressProductRegisterDTO addressProductRegisterDTO){
        if(addressProductRegisterDTO == null){
            return null;
        }

        return Address.builder()
                .id(addressProductRegisterDTO.getId())
                .zipCode(addressProductRegisterDTO.getZipCode())
                .district(addressProductRegisterDTO.getDistrict())
                .city(addressProductRegisterDTO.getCity())
                .state(addressProductRegisterDTO.getState())
                .country(addressProductRegisterDTO.getCountry())
                .build();
    }
}
