package com.doconnectai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doconnectai.entity.Question;

public interface QuestionRepo extends JpaRepository<Question, Integer> {

	
}
