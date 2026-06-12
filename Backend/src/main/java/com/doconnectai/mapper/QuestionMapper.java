package com.doconnectai.mapper;

import com.doconnectai.dto.QuestionDto;
import com.doconnectai.entity.Question;
import com.doconnectai.entity.User;

public class QuestionMapper {

	public static Question toEntity(QuestionDto dto, User user) {

		Question question = new Question();

		question.setTitle(dto.getTitle());
		question.setDescription(dto.getDescription());

		question.setUser(user);

		return question;
	}

	public static QuestionDto toDto(Question question) {
		QuestionDto dto = new QuestionDto();

		dto.setId(question.getId());
		dto.setTitle(question.getTitle());
		dto.setDescription(question.getDescription());

		if (question.getUser() != null) {
		    dto.setUserName(question.getUser().getName());
		} else {
		    dto.setUserName("Unknown");
		}


		return dto;
	}

}
