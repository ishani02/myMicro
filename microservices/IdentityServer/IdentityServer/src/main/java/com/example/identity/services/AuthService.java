package com.example.identity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.identity.entities.UserCredential;
import com.example.identity.repositories.UserCredentialRepository;

@Service
public class AuthService {
	
	@Autowired
	private UserCredentialRepository userCredentialRepository;
	
	@Autowired
	public PasswordEncoder passwordEncoder;
	
	@Autowired
	private JWTService service;
	
	public String saveUser(UserCredential userCredential) {
		userCredential.setPassword(passwordEncoder.encode(userCredential.getPassword()));
		userCredentialRepository.save(userCredential);
		return "user added to the system";
	}
	public String generateToken(String username) {
		return service.generateToken(username);
	}
	
	public void validateToken(String token) {
		service.validateToken(token);
	}
}
