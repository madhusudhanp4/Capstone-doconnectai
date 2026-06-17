package com.doconnectai.dto;

import java.time.LocalDateTime;

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
    private String message;
    private Integer roomId;
    private String userName;
    private LocalDateTime createdAt;
}