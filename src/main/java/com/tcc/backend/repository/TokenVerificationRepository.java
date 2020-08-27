package com.tcc.backend.repository;

import com.tcc.backend.model.TokenVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TokenVerificationRepository extends JpaRepository<TokenVerification, Long> {


    TokenVerification findByToken(String token);

    List<TokenVerification> findByUserId(Long id);
}
