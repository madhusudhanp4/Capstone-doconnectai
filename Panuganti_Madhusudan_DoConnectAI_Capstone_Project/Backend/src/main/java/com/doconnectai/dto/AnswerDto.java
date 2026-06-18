package com.doconnectai.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
	
	 @NotBlank(message = "Answer content cannot be empty")
	    @Size(
	        min = 5,
	        max = 5000,
	        message = "Answer must be between 5 and 5000 characters"
	    )
	private String content;
	
	
	@NotNull(message = "QuestionId is required")
	private Integer questionId;
	

	private boolean accepted;
	
	private String userName;
}
