package com.doconnectai.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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
		
		Optional<User> usrOpt = userRepo.findById(dto.getUserId());
		
		Optional <Answer> ansOpt = ansRepo.findById(dto.getAnswerId());
		
		if(usrOpt.isEmpty() || ansOpt.isEmpty()) {
			return null;
		}
		
		User u = usrOpt.get();
		Answer ans = ansOpt.get();
		
		Comment cmt = CommentMapper.toEntity(dto, u, ans);
		
		Comment saved = cmntRepo.save(cmt);
		
		return CommentMapper.toDto(saved);
	}

	@Override
	public List<CommentDto> getCommentsByAnswerId(int answerId) {
		
		List<Comment> cmts = cmntRepo.findByAnswerId(answerId);
		
		return cmts.stream()
				.map(CommentMapper::toDto)
				.collect(Collectors.toList());
	}
	
	
}
