package com.doconnectai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.doconnectai.dto.ChatMessageDto;
import com.doconnectai.entity.ChatMessage;
import com.doconnectai.entity.ChatRoom;
import com.doconnectai.entity.User;
import com.doconnectai.repository.ChatMessageRepo;
import com.doconnectai.repository.ChatRoomRepo;
import com.doconnectai.repository.UserRepo;

@Service
public class ChatMessageServiceImpl implements IChatMessageService {

	@Autowired
	private ChatMessageRepo msgRepo;

	@Autowired
	private ChatRoomRepo roomRepo;

	@Autowired
	private UserRepo userRepo;

	@Override
	public ChatMessageDto addMessage(ChatMessageDto dto) {

		String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		User user = userRepo.findByEmail(email);

		ChatRoom room = roomRepo.findById(dto.getRoomId()).orElseThrow(() -> new RuntimeException("Room not found"));

		ChatMessage msg = new ChatMessage();

		msg.setMessage(dto.getMessage());

		msg.setUser(user);

		msg.setRoom(room);

		msg = msgRepo.save(msg);

		return new ChatMessageDto(msg.getId(), msg.getMessage(), room.getId(), user.getName(), msg.getCreatedAt());
	}

	@Override
	public List<ChatMessageDto> getMessagesByRoom(Integer roomId) {

		return msgRepo.findByRoomIdOrderByCreatedAtAsc(roomId).stream().map(m -> new ChatMessageDto(m.getId(),
				m.getMessage(), m.getRoom().getId(), m.getUser().getName(), m.getCreatedAt())).toList();
	}
}