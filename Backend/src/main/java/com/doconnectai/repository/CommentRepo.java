package com.doconnectai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doconnectai.entity.Comment;


public interface CommentRepo extends JpaRepository<Comment, Integer> {

	List<Comment> findByAnswerId(int answerId);
}
