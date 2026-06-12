package com.doconnectai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.doconnectai.entity.Answer;
import com.doconnectai.repository.AnswerRepo;

public class AnswerServiceImpl implements IAnswerService {

	@Autowired
	private AnswerRepo answrRepo;
	
	@Override
	public Answer addAnswer(Answer answer) {
		
		return answrRepo.save(answer);
	}

	@Override
	public List<Answer> getAnswerByQuestionId(int questionId) {
		
		return answrRepo.findByQuestionId(questionId);
	}

}
