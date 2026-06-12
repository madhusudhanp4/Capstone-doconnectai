package com.doconnectai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doconnectai.dto.UserDto;
import com.doconnectai.service.UserServiceImpl;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

	@Autowired
	private UserServiceImpl userService;

	@PostMapping("/register")
	public UserDto registerUser(@RequestBody UserDto userDTO) {

		return userService.registerUser(userDTO);

	}

	@GetMapping("/{id}")
	public UserDto getUserById(@PathVariable int id) {

		return userService.getUserById(id);

	}

}
