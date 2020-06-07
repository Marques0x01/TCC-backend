package com.tcc.backend.dto;

import com.tcc.backend.model.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@Builder
public class UserRegisterDTO {

  private String name;
  private String email;
  private String cpf;
  private String password;
  private String phoneNumber;
  private UserStatus status;
  private List<Role> roles;
  private List<AddressWithoutObjDTO> address;

  public static UserRegisterDTO from(User user){
    if(user == null){
      return null;
    }

    return UserRegisterDTO.builder()
            .name(user.getName())
            .email(user.getEmail())
            .cpf(user.getCpf())
            .password(user.getPassword())
            .phoneNumber(user.getPhoneNumber())
            .status(user.getStatus())
            .roles(user.getRoles())
            .address(user.getAddress().stream().map(AddressWithoutObjDTO::from).collect(Collectors.toList()))
            .build();
  }

  public static User to(UserRegisterDTO userDto){

    if(userDto == null){
      return null;
    }



    return User.builder()
            .name(userDto.name)
            .email(userDto.email)
            .cpf(userDto.cpf)
            .password(userDto.password)
            .phoneNumber(userDto.phoneNumber)
            .status(userDto.status)
            .roles(userDto.roles)
            .address(userDto.getAddress().stream().map(AddressWithoutObjDTO::to).collect(Collectors.toList()))
            .build();
  }

}
