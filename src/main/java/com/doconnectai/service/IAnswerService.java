package com.doconnectai.service;

import java.util.List;

import com.doconnectai.entity.Answer;

public interface IAnswerService {
	
	Answer addAnswer(Answer answer);
	
	List<Answer> getAnswerByQuestionId(int questionId);

}
