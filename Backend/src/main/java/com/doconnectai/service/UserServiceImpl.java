package com.doconnectai.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doconnectai.dto.UserDto;
import com.doconnectai.entity.User;
import com.doconnectai.mapper.UserMapper;
import com.doconnectai.repository.UserRepo;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public UserDto registerUser(UserDto userDto) {
		
		User user = UserMapper.toEntity(userDto);
		
		user.setPassword("12345");
		
		User savedUser = userRepo.save(user);
		
		return UserMapper.toDto(savedUser);
	}

	@Override
	public UserDto getUserById(int id) {
		
		Optional<User>user = userRepo.findById(id);
		return user.map(UserMapper::toDto).orElse(null);
	}

	@Override
	public UserDto getUserByEmail(String email) {
		
		User user = userRepo.findByEmail(email);
		
		if(user == null) return null;
		
		return UserMapper.toDto(user);
	}

	
}
