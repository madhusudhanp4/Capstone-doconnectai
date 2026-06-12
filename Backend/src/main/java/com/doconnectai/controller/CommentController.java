package com.doconnectai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doconnectai.dto.CommentDto;
import com.doconnectai.service.ICommentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/comments")
@CrossOrigin
public class CommentController {

	@Autowired
	private ICommentService cmtService;

	@PostMapping
	public CommentDto addCmt(@Valid @RequestBody CommentDto dto) {

		return cmtService.addComment(dto);
	}

	@GetMapping("/answer/{answerId}")
	public List<CommentDto> getCommentsByAnswerId(@PathVariable int answerId) {
		return cmtService.getCommentsByAnswerId(answerId);
	}
	
	
}
