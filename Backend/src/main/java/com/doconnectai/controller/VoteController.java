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

@RestController
@RequestMapping("votes")
@CrossOrigin
public class VoteController {

	@Autowired
	private IVoterService vService;

	@PostMapping
	public VoteDto addVote(@Valid @RequestBody VoteDto dto) {

		return vService.addVote(dto);
	}

	@DeleteMapping("/{id}")
	public String removeVote(@PathVariable Integer id) {

		vService.removeVote(id);

		return "Vote removed successfully";
	}

	@GetMapping("/count/{answerId}")
	public Integer getVoteCount(@PathVariable Integer answerId) {

		return vService.getVoteCount(answerId);
	}

	@GetMapping("/answer/{answerId}")
	public List<VoteDto> getVotesByAnswerId(@PathVariable Integer answerId) {

		return vService.getVotesByAnswerId(answerId);
	}
}
