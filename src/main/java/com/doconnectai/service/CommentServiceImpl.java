package com.doconnectai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doconnectai.entity.Comment;
import com.doconnectai.repository.CommentRepo;

@Service
public class CommentServiceImpl implements ICommentService {
	
	@Autowired
	private CommentRepo cmntRepo;

	@Override
	public Comment addComment(Comment cmnt) {
		
		return cmntRepo.save(cmnt);
	}

	@Override
	public List<Comment> getCommentsByAnswerId(int answerId) {
		
		return cmntRepo.findByAnswerId(answerId);
	}
	
	
}
