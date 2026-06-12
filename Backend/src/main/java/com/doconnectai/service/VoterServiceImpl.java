package com.doconnectai.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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
	public VoteDto addVote(VoteDto vote) {

		Optional<User> userOpt = uRepo.findById(vote.getUserId());
		Optional<Answer> answerOpt = ansRepo.findById(vote.getAnswerId());

		if(userOpt.isEmpty()) {
		    throw new RuntimeException("User not found with id: " + vote.getUserId());
		}

		if(answerOpt.isEmpty()) {
		    throw new RuntimeException("Answer not found with id: " + vote.getAnswerId());
		}
		
		User u = userOpt.get();
		Answer ans = answerOpt.get();
		
		Vote v = VoteMapper.toEntity(vote, u, ans);
		
		Vote saved = voterRepo.save(v);
		
		return VoteMapper.toDto(saved);
	}

	@Override
	public List<VoteDto> getVotesByAnswerId(int answerId) {

		List<Vote> votes = voterRepo.findByAnswerId(answerId);
		
		return votes.stream()
				.map(VoteMapper::toDto)
				.collect(Collectors.toList());
	}

}
