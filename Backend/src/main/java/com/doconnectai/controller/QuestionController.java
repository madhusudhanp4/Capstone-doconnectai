package com.doconnectai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR', 'USER')")
	@PostMapping
	public QuestionDto addQuestion(@Valid @RequestBody QuestionDto dto) {
		
		return qstnService.addQuestion(dto);
	}
	
	@GetMapping
	public List<QuestionDto> getAllQuestions(){
		return qstnService.getAllQuestion();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR', 'USER')")
	@GetMapping("/{id}")
	public QuestionDto getQuestionById(@PathVariable Integer id) {
		
		return qstnService.getQuestionById(id);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR', 'USER')")
	@PutMapping("/{id}")
	public QuestionDto updateQuestion( @PathVariable Integer id, @Valid @RequestBody QuestionDto dto) {

	    return qstnService.updateQuestion(id, dto);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR', 'USER')")
	@DeleteMapping("/{id}")
	public String deleteQuestion(@PathVariable Integer id) {

	    qstnService.deleteQuestion(id);

	    return "Question deleted successfully";
	}

}
