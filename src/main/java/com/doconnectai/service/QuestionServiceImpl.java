package com.doconnectai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.doconnectai.entity.Question;
import com.doconnectai.repository.QuestionRepo;

public class QuestionServiceImpl implements IQuestionService {

	@Autowired
	private QuestionRepo qstnRepo;
	
	@Override
	public Question addQuestion(Question question) {
		
		return qstnRepo.save(question);
	}

	@Override
	public List<Question> getAllQuestion() {

		return qstnRepo.findAll();
	}

	@Override
	public Question getQuestionById(int id) {
		
		Optional <Question> question = qstnRepo.findById(id);
		return question.orElse(null);
	}

}
