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

@RestController
@RequestMapping("/questions")
@CrossOrigin
public class QuestionController {
	
	@Autowired
	private IQuestionService qstnService;
	
	@PostMapping
	public QuestionDto addQuestion(@RequestBody QuestionDto dto) {
		
		return qstnService.addQuestion(dto);
	}
	
	@GetMapping
	public List<QuestionDto> getAllQuestions(){
		return qstnService.getAllQuestion();
	}
	
	@GetMapping("/{id}")
	public QuestionDto getQuestionById(@PathVariable int id) {
		
		return qstnService.getQuestionById(id);
	}
	

}
