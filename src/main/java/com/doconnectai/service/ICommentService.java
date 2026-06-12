package com.doconnectai.service;

import java.util.List;

import com.doconnectai.entity.Comment;

public interface ICommentService {
	
	Comment addComment(Comment cmnt);

	List<Comment> getCommentsByAnswerId(int answerId);
}
