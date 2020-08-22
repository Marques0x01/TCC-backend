package com.tcc.backend.dto;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import com.tcc.backend.model.Role;
import com.tcc.backend.model.User;
import com.tcc.backend.model.UserStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
                .address(user.getAddress().stream().map(AddressWithoutObjDTO::convertToDto).collect(Collectors.toList()))
                .advertisings(user.getProducts().stream().map(ProductWithoutObjDTO::convertToDto).collect(Collectors.toList()))
                .favorites(user.getFavorites().stream().map(ProductWithoutObjDTO::convertToDto).collect(Collectors.toList()))
                .complaints(user.getComplaints().stream().map(ComplaintWithoutObjDTO::from).collect(Collectors.toList()))
                .build();
    }
    
    public String newPassword() {
        byte[] array = new byte[7]; 
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));
     
        return generatedString;
    }
}
