package com.doconnectai.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.doconnectai.dto.ChatRoomDto;
import com.doconnectai.entity.ChatRoom;
import com.doconnectai.repository.ChatRoomRepo;

class ChatRoomServiceImplTest {

    @InjectMocks
    private ChatRoomServiceImpl chatRoomService;

    @Mock
    private ChatRoomRepo roomRepo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // -------------------- ADD ROOM -------------------- //

    @Test
    void testAddRoom() {
        ChatRoomDto dto = new ChatRoomDto(null, "Room1", "Test Room");

        ChatRoom savedRoom = new ChatRoom();
        savedRoom.setId(1);
        savedRoom.setName("Room1");
        savedRoom.setDescription("Test Room");

        when(roomRepo.save(any(ChatRoom.class))).thenReturn(savedRoom);

        ChatRoomDto result = chatRoomService.addRoom(dto);

        assertNotNull(result);
        assertEquals("Room1", result.getName());
        assertEquals("Test Room", result.getDescription());
        assertEquals(1, result.getId());

        verify(roomRepo).save(any(ChatRoom.class));
    }

    // -------------------- GET ALL ROOMS -------------------- //

    @Test
    void testGetAllRooms() {
        ChatRoom room1 = new ChatRoom();
        room1.setId(1);
        room1.setName("Room1");
        room1.setDescription("Desc1");

        ChatRoom room2 = new ChatRoom();
        room2.setId(2);
        room2.setName("Room2");
        room2.setDescription("Desc2");

        when(roomRepo.findAll()).thenReturn(List.of(room1, room2));

        List<ChatRoomDto> result = chatRoomService.getAllRooms();

        assertEquals(2, result.size());
        assertEquals("Room1", result.get(0).getName());
        assertEquals("Room2", result.get(1).getName());
    }

    // -------------------- GET ROOM BY ID -------------------- //

    @Test
    void testGetRoomById_Success() {
        ChatRoom room = new ChatRoom();
        room.setId(1);
        room.setName("Room1");
        room.setDescription("Test Room");

        when(roomRepo.findById(1)).thenReturn(Optional.of(room));

        ChatRoomDto result = chatRoomService.getRoomById(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Room1", result.getName());
    }

    @Test
    void testGetRoomById_NotFound() {
        when(roomRepo.findById(1)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            chatRoomService.getRoomById(1);
        });

        assertEquals("Room not found", exception.getMessage());
    }

    // -------------------- DELETE ROOM -------------------- //

    @Test
    void testDeleteRoom() {
        doNothing().when(roomRepo).deleteById(1);

        chatRoomService.deleteRoom(1);

        verify(roomRepo, times(1)).deleteById(1);
    }
}