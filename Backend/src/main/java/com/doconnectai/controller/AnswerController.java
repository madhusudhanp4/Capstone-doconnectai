package com.doconnectai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doconnectai.dto.AnswerDto;
import com.doconnectai.service.IAnswerService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/answers")
@CrossOrigin
public class AnswerController {

	@Autowired
	private IAnswerService ansService;
	
	
	/*
	 * { 
	 * 		"questionId": *****,
	 * 		"content": ******
	 * }
	 */

	
	@PostMapping
	public AnswerDto addans(@Valid @RequestBody AnswerDto dto) {
		
		log.info("POST /answer added");
		return ansService.addAnswer(dto);
	}

	
	
	
	@GetMapping("/question/{questionId}")
	public List<AnswerDto> getAnswersByQuestionId(@PathVariable int questionId) {
		
		log.info("GET /answer called by question id");
		return ansService.getAnswerByQuestionId(questionId);
	}
	
	

	@PutMapping("/{id}")
	public AnswerDto updateAnswer(@PathVariable Integer id, @Valid @RequestBody AnswerDto dto) {

		log.info("UPDATE /answer updated");

		return ansService.updateAnswer(id, dto);
	}

	
	
	@DeleteMapping("/{id}")
	public String deleteAnswer(@PathVariable Integer id) {

		log.info("DELETE /answer deleted");

		ansService.deleteAnswer(id);

		return "Answer deleted successfully";
	}
	
	

		/**
	     * Accept endpoint is restricted:
	     * Only the question owner is allowed to mark
	     * an answer as accepted (validated in service layer).
	     */
	
	
	@PutMapping("/{id}/accept")
	public AnswerDto acceptAnswer(@PathVariable Integer id) {
		return ansService.acceptAnswer(id);
	}
	
	

	@GetMapping
	public List<AnswerDto> getAllAnswers() {
		return ansService.getAllAnswers();
	}
}
