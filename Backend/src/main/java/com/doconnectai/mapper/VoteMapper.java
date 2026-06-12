package com.doconnectai.mapper;

import com.doconnectai.dto.VoteDto;
import com.doconnectai.entity.Answer;
import com.doconnectai.entity.User;
import com.doconnectai.entity.Vote;

public class VoteMapper {

	public static Vote toEntity(VoteDto dto, User user, Answer answer) {

		Vote vote = new Vote();
		vote.setId(dto.getId());
		vote.setType(dto.getType());
		vote.setUser(user);
		vote.setAnswer(answer);
		return vote;
	}

	public static VoteDto toDto(Vote vote) {
		VoteDto dto = new VoteDto();

		dto.setId(vote.getId());
		dto.setType(vote.getType());

		if (vote.getUser() != null) {
			dto.setUserId(vote.getUser().getId());
			dto.setUserName(vote.getUser().getName());
		}

		if (vote.getAnswer() != null) {
			dto.setAnswerId(vote.getAnswer().getId());
		}

		return dto;
	}

}
