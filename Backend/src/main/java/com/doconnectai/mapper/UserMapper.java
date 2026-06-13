package com.doconnectai.mapper;

import com.doconnectai.dto.UserDto;
import com.doconnectai.entity.Role;
import com.doconnectai.entity.User;

public class UserMapper {
	
	public static User toEntity(UserDto dto) {
		
		User user = new User();
		user.setId(dto.getId());
		user.setName(dto.getName());
		user.setEmail(dto.getEmail());
		
		return user;
	}
	
	
	public static UserDto toDto(User user) {
		
		UserDto dto = new UserDto();
		dto.setId(user.getId());
		dto.setName(user.getName());
		dto.setEmail(user.getEmail());
		dto.setRole(user.getRole().name());
		
		return dto;
	}
}
