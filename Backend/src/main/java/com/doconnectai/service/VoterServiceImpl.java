package com.doconnectai.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.doconnectai.dto.VoteDto;
import com.doconnectai.entity.Answer;
import com.doconnectai.entity.User;
import com.doconnectai.entity.Vote;
import com.doconnectai.entity.VoteType;
import com.doconnectai.exception.ResourceNotFoundException;
import com.doconnectai.mapper.VoteMapper;
import com.doconnectai.repository.AnswerRepo;
import com.doconnectai.repository.UserRepo;
import com.doconnectai.repository.VoteRepo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VoterServiceImpl implements IVoteService {

	@Autowired
	private VoteRepo voterRepo;

	@Autowired
	private UserRepo uRepo;

	@Autowired
	private AnswerRepo ansRepo;

	@Override
	public VoteDto addVote(VoteDto dto) {

		log.info("User voting {} on answer {}", dto.getType(), dto.getAnswerId());

		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = uRepo.findByEmail(email);

		Answer answer = ansRepo.findById(dto.getAnswerId()).orElseThrow(() -> {
			log.error("Answer not found with id {}", dto.getAnswerId());
			return new ResourceNotFoundException("Answer not found");
		});

		Optional<Vote> existingVoteOpt = voterRepo.findByUser_IdAndAnswer_Id(user.getId(), answer.getId());

		VoteType newType = dto.getType();

		if (existingVoteOpt.isEmpty()) {

			Vote vote = VoteMapper.toEntity(dto, user, answer);

			if (newType == VoteType.UPVOTE) {
				answer.setVoteCount(answer.getVoteCount() + 1);
			} else {
				answer.setVoteCount(answer.getVoteCount() - 1);
			}

			ansRepo.save(answer);
			Vote saved = voterRepo.save(vote);

			log.info("New vote added. AnswerId={}, NewCount={}", answer.getId(), answer.getVoteCount());

			return VoteMapper.toDto(saved);
		}

		Vote existingVote = existingVoteOpt.get();

		if (existingVote.getType() == newType) {
			log.info("User already performed same vote. No change.");
			return VoteMapper.toDto(existingVote);
		}

		VoteType oldType = existingVote.getType();

		existingVote.setType(newType);

		if (oldType == VoteType.UPVOTE && newType == VoteType.DOWNVOTE) {
			answer.setVoteCount(answer.getVoteCount() - 2);
		} else if (oldType == VoteType.DOWNVOTE && newType == VoteType.UPVOTE) {
			answer.setVoteCount(answer.getVoteCount() + 2);
		}

		ansRepo.save(answer);
		Vote updated = voterRepo.save(existingVote);

		log.info("Vote updated. AnswerId={}, NewCount={}", answer.getId(), answer.getVoteCount());

		return VoteMapper.toDto(updated);
	}

	@Override
	public void removeVote(Integer answerId) {

		log.info("Removing vote for answer {}", answerId);

		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = uRepo.findByEmail(email);

		Answer answer = ansRepo.findById(answerId).orElseThrow(() -> new ResourceNotFoundException("Answer not found"));

		Vote vote = voterRepo.findByUser_IdAndAnswer_Id(user.getId(), answer.getId()).orElseThrow(() -> {
			log.warn("Vote not found for user {} and answer {}", user.getId(), answerId);
			return new ResourceNotFoundException("Vote not found");
		});

		if (vote.getType() == VoteType.UPVOTE) {
			answer.setVoteCount(answer.getVoteCount() - 1);
		} else {
			answer.setVoteCount(answer.getVoteCount() + 1);
		}

		ansRepo.save(answer);
		voterRepo.delete(vote);

		log.info("Vote removed successfully. AnswerId={}, NewCount={}", answer.getId(), answer.getVoteCount());
	}

	@Override
	public List<VoteDto> getVotesByAnswerId(Integer answerId) {

		log.info("Fetching votes for answer {}", answerId);

		List<Vote> votes = voterRepo.findByAnswer_Id(answerId);

		return votes.stream().map(VoteMapper::toDto).collect(Collectors.toList());
	}
}
