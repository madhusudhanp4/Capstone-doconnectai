package com.doconnectai.service;

import com.doconnectai.dto.UserDto;

public interface IUserService {
	
	UserDto registerUser(UserDto user);
	
	UserDto getUserById(int id);
	
	UserDto getUserByEmail(String email);
	
	UserDto updateUser(Integer id, UserDto dto);
	
	void deleteUser(Integer id);

}
