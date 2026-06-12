package com.doconnectai.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.doconnectai.dto.VoteDto;
import com.doconnectai.entity.Answer;
import com.doconnectai.entity.User;
import com.doconnectai.entity.Vote;
import com.doconnectai.mapper.VoteMapper;
import com.doconnectai.repository.AnswerRepo;
import com.doconnectai.repository.UserRepo;
import com.doconnectai.repository.VoteRepo;

@Service
public class VoterServiceImpl implements IVoterService {

	@Autowired
	private VoteRepo voterRepo;

	@Autowired
	private UserRepo uRepo;

	@Autowired
	private AnswerRepo ansRepo;

	@Override
	public VoteDto addVote(VoteDto dto) {

		String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		User user = uRepo.findByEmail(email);

		if (user == null) {
			throw new RuntimeException("User not found");
		}

		Answer answer = ansRepo.findById(dto.getAnswerId())
				.orElseThrow(() -> new RuntimeException("Answer not found"));

		Vote vote = VoteMapper.toEntity(dto, user, answer);
		
		Vote saved = voterRepo.save(vote);
		
		return VoteMapper.toDto(saved);
	}

	@Override
	public List<VoteDto> getVotesByAnswerId(Integer answerId) {

		List<Vote> votes = voterRepo.findByAnswerId(answerId);

		return votes.stream().map(VoteMapper::toDto).collect(Collectors.toList());
	}

}
