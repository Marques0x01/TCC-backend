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
public class AddressUserViewDTO {

    private Long id;
    private String zipCode;
    private Long number;
    private String complement;
    private String district;
    private String city;
    private String state;
    private String country;
    private Long userId;

    public static AddressUserViewDTO convertToDto(Address address){
        if(address == null){
            return null;
        }

        return AddressUserViewDTO.builder()
                .id(address.getId())
                .zipCode(address.getZipCode())
                .complement(address.getComplement())
                .number(address.getNumber())
                .district(address.getDistrict())
                .city(address.getCity())
                .state(address.getState())
                .country(address.getCountry())
                .userId(address.getUser().getId())
                .build();
    }

    public static Address convertToModel(AddressUserViewDTO dto){
        if(dto == null){
            return null;
        }

        return Address.builder()
                .id(dto.getId())
                .zipCode(dto.getZipCode())
                .complement(dto.getComplement())
                .number(dto.getNumber())
                .district(dto.getDistrict())
                .city(dto.getCity())
                .state(dto.getState())
                .country(dto.getCountry())
                .build();
    }
}
