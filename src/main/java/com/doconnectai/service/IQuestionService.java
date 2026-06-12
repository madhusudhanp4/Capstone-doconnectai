package com.doconnectai.service;

import java.util.List;

import com.doconnectai.entity.Question;

public interface IQuestionService {
	
	Question addQuestion(Question question);
	
	List<Question> getAllQuestion();
	
	Question getQuestionById(int id);

}
