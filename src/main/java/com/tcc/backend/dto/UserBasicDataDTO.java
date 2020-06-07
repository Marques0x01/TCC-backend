package com.tcc.backend.dto;

import com.tcc.backend.model.Role;
import com.tcc.backend.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class UserBasicDataDTO {

    private String name;
    private String email;
    private List<Role> roles;
    private String token;

    public static UserBasicDataDTO from(User user){
        if(user == null){
            return null;
        }

        return UserBasicDataDTO.builder()
                .name(user.getName())
                .email(user.getEmail())
                .roles(user.getRoles())
                .build();
    }

    public static User to(UserBasicDataDTO userDto){

        if(userDto == null){
            return null;
        }

        return User.builder()
                .name(userDto.name)
                .email(userDto.email)
                .roles(userDto.roles)
                .build();
    }

}
