package com.doconnectai.dto;

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
	
	private String content;
	
	private int userId;
	
	private String userName;
	
	private int questionId;

}
