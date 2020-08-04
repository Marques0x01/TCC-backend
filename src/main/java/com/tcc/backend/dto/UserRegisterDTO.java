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
  private String lastName;
  private String email;
  private String password;

  public static UserRegisterDTO from(User user){
    if(user == null){
      return null;
    }

    return UserRegisterDTO.builder()
            .name(user.getName())
            .lastName(user.getLastName())
            .email(user.getEmail())
            .password(user.getPassword())
            .build();
  }

  public static User to(UserRegisterDTO userDto){

    if(userDto == null){
      return null;
    }



    return User.builder()
            .name(userDto.name)
            .lastName(userDto.lastName)
            .email(userDto.email)
            .password(userDto.password)
            .build();
  }

}
