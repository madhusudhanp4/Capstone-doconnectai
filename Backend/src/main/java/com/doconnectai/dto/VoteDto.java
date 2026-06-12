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
	
	private Integer id;
	
	@NotBlank(message = "Vote type is required")
	private String type;
	
	private String userName;
	
	@NotNull(message = "AnswerId is required")
	private Integer answerId;
	
}
