package com.doconnectai.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {
	
	private Integer id;
	
	@NotBlank(message = "Name is required")
	private String name;
	
	@Size(min = 6, message = "Password should contain atleast 8 characters")
	private String password;
	
	@Email(message = "Invalid Email format")
	private String email;

	private String role;
	
	

}
