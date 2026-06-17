package com.doconnectai.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomDto {

    private Integer id;
    
    

    @NotBlank(message = "Room name is required")
    @Size(
        min = 3,
        max = 50,
        message = "Room name must be between 3 and 50 characters"
    		)
    private String name;
    
    
    @NotBlank(message = "Description is required")
    @Size(
        min = 5,
        max = 300,
        message = "Description must be between 5 and 300 characters"
    )
    private String description;
}