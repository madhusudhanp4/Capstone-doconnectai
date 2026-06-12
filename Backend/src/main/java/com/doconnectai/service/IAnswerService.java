package com.doconnectai.service;

import java.util.List;

import com.doconnectai.dto.AnswerDto;

public interface IAnswerService {
	
	AnswerDto addAnswer(AnswerDto answer);
	
	List<AnswerDto> getAnswerByQuestionId(int questionId);

}
