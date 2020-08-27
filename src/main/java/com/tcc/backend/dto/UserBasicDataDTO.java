package com.tcc.backend.dto;

import com.tcc.backend.model.Role;
import com.tcc.backend.model.User;
import com.tcc.backend.model.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class UserBasicDataDTO {

    private Long id;
    private String name;
    private String email;
    private List<Role> roles;
    @Enumerated(EnumType.STRING)
    private UserStatus status;
    private String phoneNumber;
    private String token;

    public static UserBasicDataDTO convertToDto(User user){
        if(user == null){
            return null;
        }

        return UserBasicDataDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .status(user.getStatus())
                .phoneNumber(user.getPhoneNumber())
                .roles(user.getRoles())
                .build();
    }

    public static User convertToModel(UserBasicDataDTO userDto){

        if(userDto == null){
            return null;
        }

        return User.builder()
                .id(userDto.id)
                .name(userDto.name)
                .status(userDto.status)
                .email(userDto.email)
                .roles(userDto.roles)
                .build();
    }

}
