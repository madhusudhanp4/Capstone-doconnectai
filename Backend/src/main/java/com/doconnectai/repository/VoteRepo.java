package com.doconnectai.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doconnectai.entity.Vote;

public interface VoteRepo extends JpaRepository<Vote, Integer> {

	List<Vote> findByAnswer_Id(int answerId);

	Optional<Vote> findByUser_IdAndAnswer_Id(Integer userId, Integer answerId);
	
	void deleteByAnswer_Id(Integer answerId);
	long countByAnswer_Id(Integer answerId);
}
