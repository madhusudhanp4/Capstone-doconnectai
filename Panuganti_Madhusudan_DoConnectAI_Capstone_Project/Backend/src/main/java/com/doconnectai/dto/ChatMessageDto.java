package com.doconnectai.dto;

import java.time.LocalDateTime;

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
public class ChatMessageDto {

    private Integer id;
    
    @NotBlank(message = "Message cannot be empty")
    @Size(
        min = 1,
        max = 1000,
        message = "Message must be between 1 and 1000 characters"
    )
    private String message;
    
    
    private Integer roomId;
   
    private String userName;
    
    private LocalDateTime createdAt;
}