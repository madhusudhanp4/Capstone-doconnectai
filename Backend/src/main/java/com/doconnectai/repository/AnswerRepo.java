package com.doconnectai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doconnectai.entity.Answer;


public interface AnswerRepo extends JpaRepository<Answer, Integer> {
	
	List<Answer> findByQuestion_Id(Integer questionId);
}
