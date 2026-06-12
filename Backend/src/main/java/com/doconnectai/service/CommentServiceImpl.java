package com.doconnectai.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.doconnectai.dto.CommentDto;
import com.doconnectai.entity.Answer;
import com.doconnectai.entity.Comment;
import com.doconnectai.entity.User;
import com.doconnectai.mapper.CommentMapper;
import com.doconnectai.repository.AnswerRepo;
import com.doconnectai.repository.CommentRepo;
import com.doconnectai.repository.UserRepo;

@Service
public class CommentServiceImpl implements ICommentService {

	@Autowired
	private CommentRepo cmntRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private AnswerRepo ansRepo;

	@Override
	public CommentDto addComment(CommentDto dto) {

		String email = (String) SecurityContextHolder
				.getContext()
				.getAuthentication()
				.getName();

		User user = userRepo.findByEmail(email);
		
		if (user == null) {
			throw new RuntimeException("User not found");
		}
		
	    if (dto.getAnswerId() == null) {
	        throw new RuntimeException("AnswerId is required for commenting");
	    }

		Answer answer = ansRepo.findById(dto.getAnswerId())
								.orElseThrow(() -> new 
										RuntimeException("Answer not found"));

		Comment comment = CommentMapper.toEntity(dto, user, answer);

		Comment saved = cmntRepo.save(comment);

		return CommentMapper.toDto(saved);

	}

	@Override
	public List<CommentDto> getCommentsByAnswer_Id(Integer answerId) {

		List<Comment> cmts = cmntRepo.findByAnswer_Id(answerId);

		return cmts.stream().map(CommentMapper::toDto).collect(Collectors.toList());
	}

}
