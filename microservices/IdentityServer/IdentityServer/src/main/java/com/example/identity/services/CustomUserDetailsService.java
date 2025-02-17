package com.example.identity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.identity.entities.UserCredential;
import com.example.identity.repositories.UserCredentialRepository;
import com.google.common.base.Optional;
@Component
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserCredentialRepository repository;
	
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		// TODO Auto-generated method stub
//		UserCredential credential =  repository.findByName(username);
//		return ((Object) credential).map(userCredential -> new CustomUserDetails(userCredential))
//        .orElseThrow(() -> new UsernameNotFoundException("username not found: " + username));
//		return credential.map(CustomUserDetails::new).orElseThrow(()->new UsernameNotFoundException("username not found:"+username));
//	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    Optional<UserCredential> credential = repository.findByName(username);
	    if (credential.isPresent()) {
	        return new CustomUserDetails(credential.get());
	    } else {
	        throw new UsernameNotFoundException("username not found: " + username);
	    }
	}
	
}
