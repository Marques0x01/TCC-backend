package com.tcc.backend.dto;

import com.tcc.backend.model.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressBasicaDataDTO {

    private Long id;
    private String zipCode;
    private String district;
    private String city;
    private String state;
    private String country;

    public static AddressBasicaDataDTO convertToDto(Address address){
        if(address == null){
            return null;
        }

        return AddressBasicaDataDTO.builder()
                .id(address.getId())
                .zipCode(address.getZipCode())
                .district(address.getDistrict())
                .city(address.getCity())
                .state(address.getState())
                .country(address.getCountry())
                .build();
    }
}
