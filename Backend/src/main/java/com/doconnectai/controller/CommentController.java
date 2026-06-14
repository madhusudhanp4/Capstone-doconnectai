package com.doconnectai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	private ICommentService cmntService;

	@PostMapping
	public CommentDto addCmt(@Valid @RequestBody CommentDto dto) {

		return cmntService.addComment(dto);
	}

	@GetMapping("/answer/{answerId}")
	public List<CommentDto> getCommentsByAnswerId(@PathVariable Integer answerId) {
		return cmntService.getCommentsByAnswer_Id(answerId);
	}


	@DeleteMapping("/{id}")
	public String deleteComment(@PathVariable Integer id) {

		cmntService.deleteComment(id);

		return "Comment deleted successfully";
	}

}
