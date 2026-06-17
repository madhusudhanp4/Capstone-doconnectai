package com.doconnectai.service;

import java.util.List;

import com.doconnectai.dto.ChatRoomDto;

public interface IChatRoomService {

    ChatRoomDto addRoom(ChatRoomDto dto);

    List<ChatRoomDto> getAllRooms();

    ChatRoomDto getRoomById(Integer id);

    void deleteRoom(Integer id);
}