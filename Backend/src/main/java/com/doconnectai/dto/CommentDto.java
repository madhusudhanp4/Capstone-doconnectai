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
public class CommentDto {
	
	private Integer id;
	
	@NotBlank(message = "Content is required")
	private String content;

	private String userName;
	
	@NotNull(message = "AnswerId is required")
	private Integer answerId;
	
	

}
