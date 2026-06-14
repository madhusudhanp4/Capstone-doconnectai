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
import com.doconnectai.exception.ResourceNotFoundException;
import com.doconnectai.mapper.VoteMapper;
import com.doconnectai.repository.AnswerRepo;
import com.doconnectai.repository.UserRepo;
import com.doconnectai.repository.VoteRepo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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

		log.info("Adding {} for answer ID: {}", dto.getType(), dto.getAnswerId());

		String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		User user = uRepo.findByEmail(email);

		Answer answer = ansRepo.findById(dto.getAnswerId()).orElseThrow(() -> {
			log.warn("Answer not found with ID: {}", dto.getAnswerId());

			return new ResourceNotFoundException("Answer not found with id : " + dto.getAnswerId());
		});

		Vote vote = VoteMapper.toEntity(dto, user, answer);

		if ("UPVOTE".equalsIgnoreCase(dto.getType())) {

			answer.setVoteCount(answer.getVoteCount() + 1);

			log.info("Upvote added. New score: {}", answer.getVoteCount());
		} else if ("DOWNVOTE".equalsIgnoreCase(dto.getType())) {

			answer.setVoteCount(answer.getVoteCount() - 1);

			log.info("Downvote added. New score: {}", answer.getVoteCount());
		} else {

			log.warn("Invalid vote type: {}", dto.getType());

			throw new IllegalArgumentException("Invalid vote type: " + dto.getType());
		}

		ansRepo.save(answer);

		Vote saved = voterRepo.save(vote);

		log.info("Vote saved successfully. Vote ID: {}, Answer ID: {}", saved.getId(), answer.getId());

		return VoteMapper.toDto(saved);
	}

	@Override

	public void removeVote(Integer id) {

		log.info("Removing vote with ID: {}", id);

		if (!voterRepo.existsById(id)) {
			log.warn("Vote not found with ID: {}", id);
			throw new ResourceNotFoundException("Vote not found with id : " + id);
		}

		log.info("Vote removed successfully. ID: {}", id);

		voterRepo.deleteById(id);
	}

	@Override
	public Integer getVoteCount(Integer answerId) {

		Answer answer = ansRepo.findById(answerId)
				.orElseThrow(() -> new ResourceNotFoundException("Answer not found with id : " + answerId));

		return answer.getVoteCount();
	}

	@Override
	public List<VoteDto> getVotesByAnswerId(Integer answerId) {

		List<Vote> votes = voterRepo.findByAnswerId(answerId);

		return votes.stream().map(VoteMapper::toDto).collect(Collectors.toList());
	}
}
