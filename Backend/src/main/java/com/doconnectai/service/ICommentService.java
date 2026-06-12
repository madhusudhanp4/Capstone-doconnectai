package com.doconnectai.service;

import java.util.List;

import com.doconnectai.dto.CommentDto;

public interface ICommentService {
	
	CommentDto addComment(CommentDto cmnt);

	List<CommentDto> getCommentsByAnswer_Id(Integer answerId);
}
