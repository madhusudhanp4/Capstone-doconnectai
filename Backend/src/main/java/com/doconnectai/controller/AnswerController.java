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

@RestController
@RequestMapping("/answers")
@CrossOrigin
public class AnswerController {
	
	@Autowired
	private IAnswerService ansService;
	
	@PostMapping
	public AnswerDto addans(@Valid @RequestBody AnswerDto dto) {
		return ansService.addAnswer(dto);
	}
	
	@GetMapping("/question/{questionId}")
	public List<AnswerDto> getAnswersByQuestionId(@PathVariable int questionId){
		
		return ansService.getAnswerByQuestionId(questionId);
	}
	

	@PutMapping("/{id}")
	public AnswerDto updateAnswer( @PathVariable Integer id, @Valid @RequestBody AnswerDto dto) {

	    return ansService.updateAnswer(
	            id,
	            dto);
	}
	
	
	@DeleteMapping("/{id}")
	public String deleteAnswer( @PathVariable Integer id) {

	    ansService.deleteAnswer(id);

	    return "Answer deleted successfully";
	}
}
