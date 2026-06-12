package com.doconnectai.mapper;

import com.doconnectai.dto.AnswerDto;
import com.doconnectai.entity.Answer;
import com.doconnectai.entity.Question;
import com.doconnectai.entity.User;

public class AnswerMapper {
	
	public static Answer toEntity(AnswerDto dto, User user, Question qstn) {
		
		Answer answr = new Answer();
		
		answr.setId(dto.getId());
		answr.setContent(dto.getContent());
		answr.setUser(user);
		answr.setQuestion(qstn);
		return answr;
	}
	
	public static AnswerDto toDto(Answer answr) {
		
		AnswerDto dto = new AnswerDto();
		dto.setId(answr.getId());
		dto.setContent(answr.getContent());
		
		if(answr.getUser() != null) {
			
			dto.setUserId(answr.getUser().getId());
			dto.setUserName(answr.getUser().getName());
			
		}
		
		if(answr.getQuestion() != null) {
			
			dto.setQuestionId(answr.getQuestion().getId());

		}
		
		return dto;
	}

}
