package com.doconnectai.dto;

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
	
	private String type;
	
	private int userId;
	
	private String userName;
	
	private int answerId;
	
}
