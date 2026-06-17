package com.doconnectai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doconnectai.dto.UserDto;
import com.doconnectai.entity.User;
import com.doconnectai.repository.UserRepo;
import com.doconnectai.security.JwtUtil;
import com.doconnectai.service.UserServiceImpl;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
			log.info("POST /login failed");
			throw new RuntimeException("Invalid email");
		}

		if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
			log.info("POST /login failed");
			throw new RuntimeException("Invalid password");
		}
		
		log.info("POST /login successful");
		return jwtUtil.generateToken(user.getEmail(), user.getRole().name());
	}

	@PostMapping("/register")
	public UserDto registerUser(@Valid @RequestBody UserDto userDTO) {

		log.info("POST /new user registered");
		return userService.registerUser(userDTO);

	}

	@PreAuthorize("hasAnyRole('ADMIN','MODERATOR')")
	@GetMapping("/{id}")
	public UserDto getUserById(@PathVariable int id) {
		
		log.info("GET /user by  id");

		return userService.getUserById(id);

	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/all")
	public List<UserDto> getAllUsers() {
		log.info("GET /listed all users");
		return userService.getAllUsers();
	}

	@PreAuthorize("hasAnyRole('USER', 'MODERATOR', 'ADMIN')")
	@PutMapping("/{id}")
	public UserDto updateUser(@PathVariable Integer id, @Valid @RequestBody UserDto dto) {

		log.info("PUT /user details updated");
		return userService.updateUser(id, dto);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public String deleteUser(@PathVariable Integer id) {

		userService.deleteUser(id);
		
		log.info("DELETE /user deleted");

		return "User deleted successfully";
	}
	
	
	@GetMapping("/me")
	public UserDto getCurrentUser(Authentication authentication) {

	    String email = authentication.getName();

	    return userService.getUserByEmail(email);
	}
	

}
