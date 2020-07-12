package com.tcc.backend.controller;

import com.tcc.backend.dto.UserDto;
import com.tcc.backend.model.User;
import com.tcc.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public List<UserDto> getAll(){
    List<User> users = userService.findAll();
    return users.stream().map(UserDto::from).collect(Collectors.toList());
  }

  @GetMapping("/{id}")
  @PreAuthorize("hasRole('ROLE_USER')")
  public UserDto getById(@PathVariable Long id){
    User user =  userService.findById(id);
    return UserDto.from(user);
  }

}
