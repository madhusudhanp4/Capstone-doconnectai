package com.doconnectai.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {
	
	private int id;
	
	@NotBlank(message = "Name is required")
	private String name;
	
	@NotBlank(message="Password is required")
	private String password;
	
	@Email(message = "Invalid Email format")
	private String email;
	
	private String role;
	
	

}
