package com.doconnectai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doconnectai.dto.QuestionDto;
import com.doconnectai.service.IQuestionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/questions")
@CrossOrigin (origins = "http://localhost:3000")
public class QuestionController {
	
	@Autowired
	private IQuestionService qstnService;
	
	@PostMapping
	public QuestionDto addQuestion(@Valid @RequestBody QuestionDto dto) {
		
		return qstnService.addQuestion(dto);
	}
	
	@GetMapping
	public List<QuestionDto> getAllQuestions(){
		return qstnService.getAllQuestion();
	}
	
	@GetMapping("/{id}")
	public QuestionDto getQuestionById(@PathVariable Integer id) {
		
		return qstnService.getQuestionById(id);
	}
	

}
