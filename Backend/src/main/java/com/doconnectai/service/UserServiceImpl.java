package com.doconnectai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.doconnectai.dto.UserDto;
import com.doconnectai.entity.Role;
import com.doconnectai.entity.User;
import com.doconnectai.exception.ResourceNotFoundException;
import com.doconnectai.mapper.UserMapper;
import com.doconnectai.repository.UserRepo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private BCryptPasswordEncoder pswrdEncoder;

	@Override
	public UserDto registerUser(UserDto userDto) {

		log.info("Registering user with email: {}", userDto.getEmail());

		User user = UserMapper.toEntity(userDto);

		user.setPassword(pswrdEncoder.encode(userDto.getPassword()));

		user.setRole(Role.USER);

		User savedUser = userRepo.save(user);

		log.info("User registered successfully with ID: {}", savedUser.getId());

		return UserMapper.toDto(savedUser);
	}

	@Override
	public UserDto getUserById(int id) {
		log.info("Fetching user with ID: {}", id);

		User user = userRepo.findById(id).orElseThrow(() -> {
			log.warn("User not found with ID: {}", id);
			return new ResourceNotFoundException("User not found with id : " + id);
		});

		return UserMapper.toDto(user);
	}

	@Override
	public UserDto getUserByEmail(String email) {
		log.info("Fetching user with email: {}", email);

		User user = userRepo.findByEmail(email);

		if (user == null)
			throw new ResourceNotFoundException("User not found with email");

		log.info("User found with email: {}", email);
		return UserMapper.toDto(user);
	}

	@Override
	public UserDto updateUser(Integer id, UserDto dto) {
		log.info("Updating user with ID: {}", id);

		User user = userRepo.findById(id).orElseThrow(() -> {
			log.warn("User not found with ID: {}", id);
			return new ResourceNotFoundException("User not found with id : " + id);
		});

		user.setName(dto.getName());
		user.setEmail(dto.getEmail());

		User updated = userRepo.save(user);

		log.info("User updated successfully. ID: {}", updated.getId());

		return UserMapper.toDto(updated);
	}

	@Override
	public List<UserDto> getAllUsers() {

		List<User> users = userRepo.findAll();

		return users.stream().map(UserMapper::toDto).toList();
	}

	@Override
	public void deleteUser(Integer id) {

		log.info("Deleting user with ID: {}", id);

		User user = userRepo.findById(id).orElseThrow(() -> {
			log.warn("User not found with ID: {}", id);
			return new ResourceNotFoundException("User not found with id : " + id);
		});

		userRepo.delete(user);

		log.info("User deleted successfully. ID: {}", id);
	}

}
