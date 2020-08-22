package com.tcc.backend.service;

import java.nio.charset.Charset;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tcc.backend.dto.UserEmailDto;
import com.tcc.backend.exception.CustomException;
import com.tcc.backend.model.User;
import com.tcc.backend.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Service
public class ForgotPasswordService {
	
	
	 @Autowired
	    private UserRepository userRepository;
	 
	 @Autowired
	    private PasswordEncoder passwordEncoder;
	 
	 
	 
		public String setNewPassword(UserEmailDto user) {
			if (userRepository.existsByEmail(user.getEmail())) {

				byte[] array = new byte[7];
				new Random().nextBytes(array);
				String generatedString = new String(array, Charset.forName("UTF-8"));

				User userSemPass = userRepository.findByEmail(user.getEmail());
				userSemPass.setPassword(passwordEncoder.encode(generatedString));
				return generatedString;

			} else {
				throw new CustomException("Email is not registered", HttpStatus.UNPROCESSABLE_ENTITY);

			}
		}
	 

}
