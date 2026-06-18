package com.doconnectai.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class QuestionDto {

	private Integer id;

	@Size(
			min = 5,
			max = 150,
			message = "Title must be between 5 and 150 characters"
			)
	private String title;

	@NotBlank(message = "Description is required")
	@Size(
			min = 10,
			max = 5000,
			message = "Description must be between 10 and 5000 characters"
			)
	private String description;

	private String userName;

}
