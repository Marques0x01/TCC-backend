package com.tcc.backend.controller;

import com.tcc.backend.dto.UserBasicDataDTO;
import com.tcc.backend.dto.UserLoginDTO;
import com.tcc.backend.dto.UserRegisterDTO;
import com.tcc.backend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signin")
    public UserBasicDataDTO login(@RequestBody UserLoginDTO userLoginDTO) {
        return authService.signin(userLoginDTO);
    }

    @PostMapping("/signup")
    public UserBasicDataDTO signup(@RequestBody UserRegisterDTO user) {
        return authService.signup(UserRegisterDTO.convertToDto(user));
    }
}
