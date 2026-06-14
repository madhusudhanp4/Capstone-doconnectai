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
import com.doconnectai.exception.ResourceNotFoundException;
import com.doconnectai.mapper.CommentMapper;
import com.doconnectai.repository.AnswerRepo;
import com.doconnectai.repository.CommentRepo;
import com.doconnectai.repository.UserRepo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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

		log.info("Adding comment to answer ID: {}", dto.getAnswerId());

		String email = (String) SecurityContextHolder.getContext().getAuthentication().getName();

		User user = userRepo.findByEmail(email);

		if (user == null) {
			log.warn("Authenticated user not found with email: {}", email);
			throw new ResourceNotFoundException("User not found");
		}

		if (dto.getAnswerId() == null) {
			throw new RuntimeException("AnswerId is required for commenting");
		}

		Answer answer = ansRepo.findById(dto.getAnswerId()).orElseThrow(() -> {
			log.warn("Answer not found with ID: {}", dto.getAnswerId());
			return new ResourceNotFoundException("Answer not found with id : " + dto.getAnswerId());
		});

		Comment comment = CommentMapper.toEntity(dto, user, answer);

		Comment saved = cmntRepo.save(comment);

		log.info("Comment added successfully. ID: {}", saved.getId());

		return CommentMapper.toDto(saved);
	}

	@Override
	public List<CommentDto> getCommentsByAnswer_Id(Integer answerId) {

		List<Comment> cmts = cmntRepo.findByAnswer_Id(answerId);

		return cmts.stream().map(CommentMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public void deleteComment(Integer id) {
		
		log.info("Deleting comment with ID: {}", id);

		if (!cmntRepo.existsById(id)) {
			log.warn("Comment not found with ID: {}", id);
			throw new ResourceNotFoundException("Comment not found with id : " + id);
		}
		
		log.info("Comment deleted successfully. ID: {}", id);

		cmntRepo.deleteById(id);
	}
}
