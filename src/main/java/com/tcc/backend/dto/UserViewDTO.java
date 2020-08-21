package com.tcc.backend.dto;

import com.tcc.backend.model.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserViewDTO {

    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String cpf;
    private String phoneNumber;
    private AddressUserViewDTO address;
    private String gender;

    public static UserViewDTO convertToDto(User user) {
        if(user == null){
            return null;
        }

        return UserViewDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .cpf(user.getCpf())
                .gender(user.getGender())
                .phoneNumber(user.getPhoneNumber())
                .address(AddressUserViewDTO.convertToDto(user.getAddress()))
                .build();
    }

    public static User convertToModel(UserViewDTO dto){
        if(dto == null){
            return null;
        }

        return User.builder()
                .id(dto.id)
                .name(dto.name)
                .lastName(dto.lastName)
                .email(dto.email)
                .cpf(dto.cpf)
                .gender(dto.gender)
                .phoneNumber(dto.phoneNumber)
                .address(AddressUserViewDTO.convertToModel(dto.getAddress()))
                .build();
    }
}
