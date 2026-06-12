package com.doconnectai.service;

import java.util.List;

import com.doconnectai.entity.Vote;
import com.doconnectai.repository.VoteRepo;

public class VoterServiceImpl implements IVoterService {

	private VoteRepo voterRepo;
	
	@Override
	public Vote addVote(Vote vote) {
	
		return voterRepo.save(vote);
	}

	@Override
	public List<Vote> getVotesByAnswerId(int answerId) {
		
		
		return voterRepo.findByAnswerId(answerId);
	}

}
