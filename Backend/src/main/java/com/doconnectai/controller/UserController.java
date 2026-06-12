package com.doconnectai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doconnectai.dto.UserDto;
import com.doconnectai.entity.User;
import com.doconnectai.repository.UserRepo;
import com.doconnectai.security.JwtUtil;
import com.doconnectai.service.UserServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private UserServiceImpl userService;

	@PostMapping("/login")
	public String login(@RequestBody UserDto dto) {

		User user = userRepo.findByEmail(dto.getEmail());

		if (user == null) {
			throw new RuntimeException("Invalid email");
		}

		if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
			throw new RuntimeException("Invalid password");
		}

		return jwtUtil.generateToken(user.getEmail());
	}

	@PostMapping("/register")
	public UserDto registerUser(@Valid @RequestBody UserDto userDTO) {

		return userService.registerUser(userDTO);

	}

	@GetMapping("/{id}")
	public UserDto getUserById(@PathVariable int id) {

		return userService.getUserById(id);
		
	}

}
