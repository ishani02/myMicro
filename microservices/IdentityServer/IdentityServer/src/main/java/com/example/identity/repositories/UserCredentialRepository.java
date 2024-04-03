package com.example.identity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.identity.entities.UserCredential;
import com.google.common.base.Optional;

public interface UserCredentialRepository extends JpaRepository<UserCredential, Integer>{

	Optional<UserCredential> findByName(String username);

}
