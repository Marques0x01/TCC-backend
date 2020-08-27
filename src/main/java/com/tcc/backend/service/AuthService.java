package com.tcc.backend.service;

import com.tcc.backend.dto.UserBasicDataDTO;
import com.tcc.backend.dto.UserLoginDTO;
import com.tcc.backend.exception.CustomException;
import com.tcc.backend.model.Role;
import com.tcc.backend.model.User;
import com.tcc.backend.model.UserStatus;
import com.tcc.backend.repository.AddressRepository;
import com.tcc.backend.repository.UserRepository;
import com.tcc.backend.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    public UserBasicDataDTO signin(UserLoginDTO userLogin){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLogin.getEmail(), userLogin.getPassword()));
            User user = userRepository.findByEmail(userLogin.getEmail());
            UserBasicDataDTO userData = UserBasicDataDTO.convertToDto(user);
            userData.setToken(jwtTokenProvider.createToken(userLogin.getEmail(), userRepository.findByEmail(userLogin.getEmail()).getRoles()));
            return userData;
        } catch (AuthenticationException e) {
            throw new CustomException("Invalid email/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public UserBasicDataDTO signup(User user) {
        if (!userRepository.existsByEmail(user.getEmail())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList(Role.ROLE_USER));
            user.setStatus(UserStatus.PENDING);
            userRepository.save(user);
            UserBasicDataDTO userDto = UserBasicDataDTO.convertToDto(user);
            userDto.setToken(jwtTokenProvider.createToken(user.getEmail(), user.getRoles()));
            return  userDto;
        } else {
            throw new CustomException("Email is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
