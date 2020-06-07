package com.tcc.backend.service;

import com.tcc.backend.dto.UserBasicDataDTO;
import com.tcc.backend.dto.UserLoginDTO;
import com.tcc.backend.exception.CustomException;
import com.tcc.backend.model.User;
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

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public List<User> findAll(){
    return userRepository.findAll();
  }

  public User findById(Long id) {
    if(id == null){
      throw new NullPointerException("ID can not be null");
    }

    User user = userRepository.findById(id).orElse(null);

    if(Objects.isNull(user)){
      throw new EntityNotFoundException("User not found");
    }

    return user;
  }

  public List<User> findAllById(List<Long> ids) {
    List<User> users = ids.stream().map(id -> userRepository.findById(id).orElse(null)).collect(Collectors.toList());
    return users.stream().filter(user -> user != null).collect(Collectors.toList());
  }
}
