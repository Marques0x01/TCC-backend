package com.tcc.backend.controller;

import com.tcc.backend.dto.UserDto;
import com.tcc.backend.dto.UserViewDTO;
import com.tcc.backend.model.TokenVerification;
import com.tcc.backend.model.User;
import com.tcc.backend.repository.UserRepository;
import com.tcc.backend.service.TokenVerificationService;
import com.tcc.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserService userService;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private TokenVerificationService tokenService;

  @GetMapping
  @PreAuthorize("hasRole('ROLE_USER')")
  public List<UserDto> getAll(){
    List<User> users = userService.findAll();
    return users.stream().map(UserDto::from).collect(Collectors.toList());
  }

  @GetMapping("/{id}")
  public UserViewDTO getById(@PathVariable Long id){
    User user =  userService.findById(id);
    return UserViewDTO.convertToDto(user);
  }

  @PutMapping
  public UserViewDTO update(@RequestBody UserViewDTO dto){
    User user = UserViewDTO.convertToModel(dto);
    User response = userService.update(user);
    return UserViewDTO.convertToDto(response);
  }

  @GetMapping("/confirmation-email")
  public void sendUserVerification(@RequestParam String email){
    User user = userRepository.findByEmail(email);
    if(user == null){
      throw new EntityNotFoundException("User was not found");
    }
    userService.generateUserConfirmation(user);
  }

  @GetMapping("/confirmation-token")
  public Boolean verifyUserToken(@RequestParam String token, @RequestParam String email){
    return tokenService.confirmToken(token, email);
  }

}
