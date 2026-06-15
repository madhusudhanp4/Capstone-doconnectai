package com.doconnectai.dto;

import com.doconnectai.entity.VoteType;

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
		
	@NotNull(message = "AnswerId is required")
	private Integer answerId;
	
	private VoteType type;
	
}
