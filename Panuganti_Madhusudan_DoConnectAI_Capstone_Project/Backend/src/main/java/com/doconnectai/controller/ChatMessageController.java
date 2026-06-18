package com.doconnectai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doconnectai.dto.ChatMessageDto;
import com.doconnectai.service.IChatMessageService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/messages")
@CrossOrigin
public class ChatMessageController {

	@Autowired
	private IChatMessageService msgService;

	
	
	@PostMapping
	public ChatMessageDto addMessage(@Valid @RequestBody ChatMessageDto dto) {

		return msgService.addMessage(dto);
	}

	
	
	@GetMapping("/room/{roomId}")
	public List<ChatMessageDto> getMessagesByRoom(@PathVariable Integer roomId) {

		return msgService.getMessagesByRoom(roomId);
	}



}
