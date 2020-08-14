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

    private Long id;
    private String name;
    private String email;
    private List<Role> roles;
    private Boolean isActive;
    private String token;

    public static UserBasicDataDTO from(User user){
        if(user == null){
            return null;
        }

        return UserBasicDataDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .isActive(user.getIsActive())
                .roles(user.getRoles())
                .build();
    }

    public static User to(UserBasicDataDTO userDto){

        if(userDto == null){
            return null;
        }

        return User.builder()
                .id(userDto.id)
                .name(userDto.name)
                .email(userDto.email)
                .isActive(userDto.isActive)
                .roles(userDto.roles)
                .build();
    }

}
