package com.doconnectai.service;

import java.util.List;

import com.doconnectai.dto.VoteDto;

public interface IVoterService {

    VoteDto addVote(VoteDto vote);

    void removeVote(Integer id);
    
    List<VoteDto> getVotesByAnswerId(Integer answerId);

    Integer getVoteCount(Integer answerId);
}