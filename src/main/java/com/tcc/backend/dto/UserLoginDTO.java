package com.tcc.backend.dto;

import lombok.Data;

@Data
public class UserLoginDTO {
    private String email;
    private String password;
}
