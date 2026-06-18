package com.doconnectai.service;

import java.util.List;

import com.doconnectai.dto.ChatMessageDto;

public interface IChatMessageService {

	ChatMessageDto addMessage(ChatMessageDto dto);

	List<ChatMessageDto> getMessagesByRoom(Integer roomId);
}