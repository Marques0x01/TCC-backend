package com.tcc.backend.model;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class TokenVerification {
	
	public static final String STATUS_PENDING = "Pending";
	public static final String STATUS_VERIFIED = "Verified";
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String token;
	private String status;
	private LocalDateTime expiredDateTime;
	private LocalDateTime issuedDate;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User user;
	
	
	public TokenVerification(User user) {
		
		this.token = UUID.randomUUID().toString();
		this.status = STATUS_PENDING;
		this.issuedDate = LocalDateTime.now();
		this.expiredDateTime = this.issuedDate.plusHours(3);
		this.user = user;
		
	}
	
	
}
