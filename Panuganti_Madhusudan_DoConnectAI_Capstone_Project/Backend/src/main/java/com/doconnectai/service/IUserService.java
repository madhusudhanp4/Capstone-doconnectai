package com.doconnectai.service;

import java.util.List;

import com.doconnectai.dto.UserDto;

public interface IUserService {
	
	UserDto registerUser(UserDto user);
	
	List<UserDto> getAllUsers();
	
	UserDto getUserById(int id);
	
	UserDto getUserByEmail(String email);
	
	UserDto updateUser(Integer id, UserDto dto);
	
	void deleteUser(Integer id);

}
