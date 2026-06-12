package com.doconnectai.service;

import java.util.List;

import com.doconnectai.dto.VoteDto;

public interface IVoterService {
	
	VoteDto addVote(VoteDto vote);
	
	List<VoteDto> getVotesByAnswerId(Integer answerId);

}
