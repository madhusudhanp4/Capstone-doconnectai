package com.doconnectai.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doconnectai.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {

	User findByEmail(String email);
	
	Optional<User> findOptionalByEmail(String email);
	
}
