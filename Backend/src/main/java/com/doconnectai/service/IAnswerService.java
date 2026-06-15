package com.doconnectai.service;

import java.util.List;

import com.doconnectai.dto.AnswerDto;

public interface IAnswerService {
	
	AnswerDto addAnswer(AnswerDto answer);
	
	List<AnswerDto> getAnswerByQuestionId(Integer questionId);

    AnswerDto updateAnswer( Integer id, AnswerDto dto);

    void deleteAnswer(Integer id);
    
    AnswerDto acceptAnswer(Integer answerId);
    
}
