package com.doconnectai.service;

import java.util.List;

import com.doconnectai.dto.CommentDto;
import com.doconnectai.entity.Comment;

public interface ICommentService {
	
	CommentDto addComment(CommentDto cmnt);

	List<CommentDto> getCommentsByAnswerId(int answerId);
}
