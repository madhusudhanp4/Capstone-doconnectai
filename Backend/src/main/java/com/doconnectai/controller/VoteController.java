package com.doconnectai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doconnectai.dto.VoteDto;
import com.doconnectai.service.IVoterService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("votes")
@CrossOrigin
public class VoteController {

	@Autowired
	private IVoterService vService;

	@PostMapping
	public VoteDto addVote(@Valid @RequestBody VoteDto dto) {

		log.info("POST /vote added");
		return vService.addVote(dto);
	}

	@DeleteMapping("/{id}")
	public String removeVote(@PathVariable Integer id) {

		log.info("DELETE /vote removed");
		vService.removeVote(id);

		return "Vote removed successfully";
	}

	@GetMapping("/count/{answerId}")
	public Integer getVoteCount(@PathVariable Integer answerId) {

		log.info("GET /vote count");
		return vService.getVoteCount(answerId);
	}

	@GetMapping("/answer/{answerId}")
	public List<VoteDto> getVotesByAnswerId(@PathVariable Integer answerId) {

		log.info("GET /votes by answer id");
		return vService.getVotesByAnswerId(answerId);
	}
}
