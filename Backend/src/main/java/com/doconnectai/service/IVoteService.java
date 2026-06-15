package com.doconnectai.service;

import java.util.List;

import com.doconnectai.dto.VoteDto;

public interface IVoteService {

	VoteDto addVote(VoteDto vote);

	void removeVote(Integer answerId);

	List<VoteDto> getVotesByAnswerId(Integer answerId);

}