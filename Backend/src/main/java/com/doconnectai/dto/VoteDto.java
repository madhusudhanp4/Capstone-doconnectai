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
public class VoteDto {
	
	private int id;
	
	@NotBlank(message = "Vote type is required")
	private String type;
	
	@NotNull(message = "UserId is required")
	private int userId;
	
	private String userName;
	
	@NotNull(message = "AnswerId is required")
	private int answerId;
	
}
