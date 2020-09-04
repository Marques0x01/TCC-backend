package com.tcc.backend.service;

import antlr.Token;
import com.tcc.backend.exception.CustomException;
import com.tcc.backend.model.Email;
import com.tcc.backend.model.TokenVerification;
import com.tcc.backend.model.User;
import com.tcc.backend.repository.TokenVerificationRepository;
import com.tcc.backend.repository.UserRepository;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private TokenVerificationRepository tokenVerificationRepository;
  @Autowired
  private EmailService emailService;
  @Autowired
  private PasswordEncoder passwordEncoder;
  @Autowired
  private AuthenticationManager authenticationManager;

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

  public User update(User user) {
    if(!userRepository.findById(user.getId()).isPresent()){
      return null;
    }

    User model = userRepository.findById(user.getId()).get();
    model.setCpf(user.getCpf());
    model.setPhoneNumber(user.getPhoneNumber());
    model.setGender(user.getGender());
    return userRepository.save(model);
  }

  public void generateUserConfirmation(User user){
    List<TokenVerification> tokens = tokenVerificationRepository.findByUserId(user.getId());
    if(tokens.size() > 0){
      tokenVerificationRepository.deleteAll(tokens);
    }
    TokenVerification token = new TokenVerification(user);
    tokenVerificationRepository.save(token);

    Email email = Email.builder()
                  .name(user.getName())
                  .to(user.getEmail())
                  .token(token.getToken())
                  .build();

    try {
      this.emailService.sendConfirmationEmail(email);
    } catch (MessagingException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (TemplateException e) {
      e.printStackTrace();
    }
  }

  public void updatePassword(Long id, String password, String oldPassword) {
    if(!userRepository.findById(id).isPresent()){
      throw new EntityNotFoundException("User not found");
    }

    User user = userRepository.findById(id).get();

    try{
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), oldPassword));

      user.setPassword(passwordEncoder.encode(password));
      userRepository.save(user);
    } catch (AuthenticationException e) {
      throw new CustomException("Invalid email/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
    }
  }

  public void updateEmail(String oldEmail, String email){
    User user = userRepository.findByEmail(oldEmail);

    if(user == null){
      throw new EntityNotFoundException("User not found");
    }

    if(user.getEmail().equals(email)){
      throw new IllegalArgumentException("Email needs to be different");
    }

    user.setEmail(email);
    userRepository.save(user);
  }
}
