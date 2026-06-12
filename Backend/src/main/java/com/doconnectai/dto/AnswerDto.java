package com.doconnectai.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDto {
	
	private int id;
	
	@NotBlank(message = "Content is required")
	private String content;
	
	@NotNull(message = "UserId is required")
	private int userId;
	
	private String userName;
	
	@NotNull(message = "QuestionId is required")
	private int questionId;

}
