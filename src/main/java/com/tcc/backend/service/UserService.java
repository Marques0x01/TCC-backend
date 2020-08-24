package com.tcc.backend.service;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tcc.backend.exception.CustomException;
import com.tcc.backend.model.User;
import com.tcc.backend.repository.UserRepository;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;
  
  @Autowired
  private PasswordEncoder passwordEncoder;

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
  
  
  public User findByEmail(String email) {
	  if(email == null) {
		  
		  throw new NullPointerException("Email can not be null");
	  
	 
	  }else {
		  User user = userRepository.findByEmail(email);
		  
		  if(Objects.isNull(user)){
		      throw new EntityNotFoundException("User not found");
		  }
		  
		  return user;
	  }
	  
  }
  
  public void setNewPassword(String email) {
		if (userRepository.existsByEmail(email)) {

			byte[] array = new byte[7];
			new Random().nextBytes(array);
			String generatedString = new String(array, Charset.forName("UTF-8"));

			User userSemPass = userRepository.findByEmail(email);
			userSemPass.setPassword(passwordEncoder.encode(generatedString));
			

		} else {
			throw new CustomException("Email is not registered", HttpStatus.UNPROCESSABLE_ENTITY);

		}
	}
  
  
 
  
}
