package com.doconnectai.service;

import java.util.List;

import com.doconnectai.dto.QuestionDto;

public interface IQuestionService {
	
	QuestionDto addQuestion(QuestionDto question);
	
	List<QuestionDto> getAllQuestion();
	
	QuestionDto getQuestionById(int id);

}
