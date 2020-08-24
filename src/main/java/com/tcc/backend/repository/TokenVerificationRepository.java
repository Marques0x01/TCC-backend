package com.tcc.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcc.backend.model.TokenVerification;

public interface TokenVerificationRepository extends JpaRepository<TokenVerification, Long> {
	
	

}
