package com.doconnectai.mapper;

import com.doconnectai.dto.AnswerDto;
import com.doconnectai.entity.Answer;
import com.doconnectai.entity.Question;
import com.doconnectai.entity.User;

public class AnswerMapper {
	
	public static Answer toEntity(AnswerDto dto, User user, Question qstn) {
		
		Answer answr = new Answer();
		
		answr.setContent(dto.getContent());
		
		answr.setUser(user);
		answr.setQuestion(qstn);
		
		return answr;
	}
	
	public static AnswerDto toDto(Answer answr) {
		
		AnswerDto dto = new AnswerDto();
		
		dto.setId(answr.getId());
		dto.setContent(answr.getContent());
		dto.setAccepted(answr.isAccepted());
		
		dto.setQuestionId(answr.getQuestion().getId());
		
		return dto;
	}

}
