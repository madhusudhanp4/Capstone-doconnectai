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
	
	private Integer id;
	
	@NotBlank(message = "Content is required")
	private String content;
	
	
	@NotNull(message = "QuestionId is required")
	private Integer questionId;

}
