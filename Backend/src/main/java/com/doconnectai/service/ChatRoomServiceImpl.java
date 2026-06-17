package com.doconnectai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doconnectai.dto.ChatRoomDto;
import com.doconnectai.entity.ChatRoom;
import com.doconnectai.repository.ChatRoomRepo;

@Service
public class ChatRoomServiceImpl implements IChatRoomService {

	@Autowired
	private ChatRoomRepo roomRepo;

	@Override
	public ChatRoomDto addRoom(ChatRoomDto dto) {

		ChatRoom room = new ChatRoom();

		room.setName(dto.getName());

		room.setDescription(dto.getDescription());

		room = roomRepo.save(room);

		return new ChatRoomDto(room.getId(), room.getName(), room.getDescription());
	}

	@Override
	public List<ChatRoomDto> getAllRooms() {

		return roomRepo.findAll().stream().map(r -> new ChatRoomDto(r.getId(), r.getName(), r.getDescription()))
				.toList();
	}

	@Override
	public ChatRoomDto getRoomById(Integer id) {

		ChatRoom room = roomRepo.findById(id).orElseThrow(() -> new RuntimeException("Room not found"));

		return new ChatRoomDto(room.getId(), room.getName(), room.getDescription());
	}

	@Override
	public void deleteRoom(Integer id) {

		roomRepo.deleteById(id);
	}
}