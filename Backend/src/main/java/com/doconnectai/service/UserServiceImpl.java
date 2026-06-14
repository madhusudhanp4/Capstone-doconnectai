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

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private BCryptPasswordEncoder pswrdEncoder;

	@Override
	public UserDto registerUser(UserDto userDto) {

		User user = UserMapper.toEntity(userDto);

		user.setPassword(pswrdEncoder.encode(userDto.getPassword()));

		user.setRole(Role.USER);

		User savedUser = userRepo.save(user);

		return UserMapper.toDto(savedUser);
	}

	@Override
	public UserDto getUserById(int id) {

		User user = userRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id : " + id));

		return UserMapper.toDto(user);
	}

	@Override
	public UserDto getUserByEmail(String email) {

		User user = userRepo.findByEmail(email);

		if (user == null)
			return null;

		return UserMapper.toDto(user);
	}

	@Override
	public UserDto updateUser(Integer id, UserDto dto) {

	    User user = userRepo.findById(id)
	            .orElseThrow(() ->
	                    new ResourceNotFoundException(
	                            "User not found with id : " + id));

	    user.setName(dto.getName());
	    user.setEmail(dto.getEmail());

	    User updated = userRepo.save(user);

	    return UserMapper.toDto(updated);
	}

	@Override
	public List<UserDto> getAllUsers() {

		List<User> users = userRepo.findAll();

		return users.stream().map(UserMapper::toDto).toList();
	}

	@Override
	public void deleteUser(Integer id) {

	    User user = userRepo.findById(id)
	            .orElseThrow(() ->
	                    new ResourceNotFoundException(
	                            "User not found with id : " + id));

	    userRepo.delete(user);
	}

}
