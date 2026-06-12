package com.doconnectai.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.doconnectai.entity.User;
import com.doconnectai.repository.UserRepo;

public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public User registerUser(User user) {
		
		return userRepo.save(user);
	}

	@Override
	public User getUserByIdUser(int id) {
		Optional<User>user = userRepo.findById(id);
		return user.orElse(null);
	}

	@Override
	public User getUserByEmail(String email) {
		
		return userRepo.findByEmail(email);
	}

	
}
