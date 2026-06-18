package com.doconnectai.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;
	
	
	
	/**
	 * Global exception handler for handling all application-wide exceptions.
	 * Ensures consistent error responses and centralized logging.
	 */
	

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	
	
	/**
	 * 	Handles ResourceNotFoundException.
	 * 	Triggered when a requested resource is not found.
	 * 	Returns HTTP 404 response with error message.
	 */
	
		
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException e) {

		log.error("Resource not found: {}", e.getMessage());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	}
	
		

	/**
	 * Handles validation errors.
	 * Triggered when @Valid request body validation fails.
	 * Collects field-wise errors and returns HTTP 400 response.
	 */


	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException ex) {

		Map<String, String> errors = new HashMap<>();

		ex.getBindingResult().getFieldErrors()
				.forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

		log.warn("Validation failed: {}", errors);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}

	
	
	
	/**
	 * Handles all unhandled exceptions.
	 * Acts as a fallback for unexpected errors.
	 * Returns generic HTTP 500 response without exposing internal details.
	 */

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleException(Exception e) {

		log.error("Unhandled exception occurred", e);

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
	}
}