package com.doconnectai.mapper;

import com.doconnectai.dto.CommentDto;
import com.doconnectai.entity.Answer;
import com.doconnectai.entity.Comment;
import com.doconnectai.entity.Question;
import com.doconnectai.entity.User;

public class CommentMapper {

	
	
	public static Comment toEntity(CommentDto dto, User user, Answer ans) {

		Comment cmt = new Comment();

		cmt.setContent(dto.getContent());
		cmt.setUser(user);
		cmt.setAnswer(ans);

		return cmt;
	}
	
	

	public static CommentDto toDto(Comment cmt) {

		CommentDto dto = new CommentDto();

		dto.setId(cmt.getId());
		dto.setContent(cmt.getContent());

		dto.setAnswerId(cmt.getAnswer().getId());
		
		dto.setUserName(cmt.getUser().getName());

	

		return dto;
	}

}
