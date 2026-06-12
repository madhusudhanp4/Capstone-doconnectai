package com.doconnectai.mapper;

import com.doconnectai.dto.CommentDto;
import com.doconnectai.entity.Answer;
import com.doconnectai.entity.Comment;
import com.doconnectai.entity.User;

public class CommentMapper {
	
	public static Comment toEntity(CommentDto dto, User user, Answer ans) {
		
		Comment cmt = new Comment();
		
		cmt.setId(dto.getId());
		cmt.setContent(dto.getContent());
		cmt.setUser(user);
		cmt.setAnswer(ans);
		
		return cmt;
	}
	
	public static CommentDto toDto(Comment cmt) {
		
		CommentDto dto = new CommentDto();
		
		dto.setId(cmt.getId());
		dto.setContent(cmt.getContent());
		
		if(cmt.getUser()!= null) {
			dto.setUserId(cmt.getUser().getId());
			
			dto.setUserName(cmt.getUser().getName());
		}
		
		if(cmt.getAnswer() != null) {
			
			dto.setAnswerId(cmt.getAnswer().getId());
		}
		
		return dto;
	}

}
