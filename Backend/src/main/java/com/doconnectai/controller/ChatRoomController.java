package com.doconnectai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doconnectai.dto.ChatRoomDto;
import com.doconnectai.service.IChatRoomService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/chatrooms")
@CrossOrigin
public class ChatRoomController {

	@Autowired
	private IChatRoomService roomService;

	
	
	
	@PostMapping
	public ChatRoomDto addRoom(@Valid @RequestBody ChatRoomDto dto) {

		return roomService.addRoom(dto);
	}

	
	
	
	@GetMapping
	public List<ChatRoomDto> getAllRooms() {

		return roomService.getAllRooms();
	}
	
	
	

	@GetMapping("/{id}")
	public ChatRoomDto getRoomById(@PathVariable Integer id) {

		return roomService.getRoomById(id);
	}

	
	
	
	@DeleteMapping("/{id}")
	public void deleteRoom(@PathVariable Integer id) {

		roomService.deleteRoom(id);
	}
	
	
}