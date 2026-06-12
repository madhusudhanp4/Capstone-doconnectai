package com.doconnectai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doconnectai.entity.Vote;


public interface VoteRepo extends JpaRepository<Vote, Integer> {
	
	List<Vote> findByAnswerId(int answerId);
}
