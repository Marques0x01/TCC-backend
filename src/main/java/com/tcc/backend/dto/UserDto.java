package com.tcc.backend.dto;

import com.tcc.backend.model.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String name;
    private String email;
    private String cpf;
    private String phoneNumber;
    private UserStatus status;
    private List<Role> roles;
    private List<AddressWithoutObjDTO> address;
    private List<ProductWithoutObjDTO> advertisings;
    private List<ProductWithoutObjDTO> favorites;
    private List<ComplaintWithoutObjDTO> complaints;


    public static UserDto from(User user){
        if(user == null){
            return null;
        }

        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .cpf(user.getCpf())
                .phoneNumber(user.getPhoneNumber())
                .status(user.getStatus())
                .roles(user.getRoles())
                .address(user.getAddress().stream().map(AddressWithoutObjDTO::from).collect(Collectors.toList()))
                .advertisings(user.getProducts().stream().map(ProductWithoutObjDTO::from).collect(Collectors.toList()))
                .favorites(user.getFavorites().stream().map(ProductWithoutObjDTO::from).collect(Collectors.toList()))
                .complaints(user.getComplaints().stream().map(ComplaintWithoutObjDTO::from).collect(Collectors.toList()))
                .build();
    }
}
