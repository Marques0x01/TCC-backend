package com.tcc.backend.service;

import com.tcc.backend.model.TokenVerification;
import com.tcc.backend.model.UserStatus;
import com.tcc.backend.repository.TokenVerificationRepository;
import com.tcc.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;

@Service
public class TokenVerificationService {

    @Autowired
    private TokenVerificationRepository tokenRepo;
    @Autowired
    private UserRepository userRepository;


    public Boolean confirmToken(String token, String email) {
        TokenVerification tokenVerification = tokenRepo.findByToken(token);
        Boolean isConfirmed = verifyToken(tokenVerification, email);

        if(!isConfirmed){
            return false;
        }

        if(tokenVerification.getUser().getStatus() != UserStatus.ACTIVE){
            tokenVerification.getUser().setStatus(UserStatus.ACTIVE);
            userRepository.save(tokenVerification.getUser());
        }

        return true;
    }

    private Boolean verifyToken(TokenVerification token, String email) {
        LocalDate today = LocalDate.now();

        if(token == null){
            return false;
        }

        if(!token.getUser().getEmail().equalsIgnoreCase(email)){
            return false;
        }

        if(today.isAfter(ChronoLocalDate.from(token.getExpiredDateTime()))){
            return false;
        }


        return true;
    }
}
