package com.doconnectai.service;

import java.util.List;

import com.doconnectai.entity.Vote;

public interface IVoterService {
	
	Vote addVote(Vote vote);
	
	List<Vote> getVotesByAnswerId(int answerId);

}
