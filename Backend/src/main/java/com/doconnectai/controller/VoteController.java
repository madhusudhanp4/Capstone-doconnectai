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
import com.doconnectai.service.IVoteService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping("/votes")
@CrossOrigin
public class VoteController {

	@Autowired
	private IVoteService vService;

	@PostMapping
	public VoteDto addVote(@Valid @RequestBody VoteDto dto) {

		log.info("POST /votes - type: {}, answerId: {}", dto.getType(), dto.getAnswerId());

		return vService.addVote(dto);
	}

	@DeleteMapping("/answer/{answerId}")
	public String removeVote(@PathVariable Integer answerId) {

		log.info("DELETE /votes for answerId: {}", answerId);

		vService.removeVote(answerId);

		return "Vote removed successfully";
	}

	
	@GetMapping("/answer/{answerId}")
	public List<VoteDto> getVotesByAnswerId(@PathVariable Integer answerId) {

		log.info("GET /votes for answerId: {}", answerId);

		return vService.getVotesByAnswerId(answerId);
	}
}