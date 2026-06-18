package com.doconnectai.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.doconnectai.dto.ChatMessageDto;
import com.doconnectai.entity.ChatMessage;
import com.doconnectai.entity.ChatRoom;
import com.doconnectai.entity.User;
import com.doconnectai.repository.ChatMessageRepo;
import com.doconnectai.repository.ChatRoomRepo;
import com.doconnectai.repository.UserRepo;

class ChatMessageServiceImplTest {

    @InjectMocks
    private ChatMessageServiceImpl messageService;

    @Mock
    private ChatMessageRepo msgRepo;

    @Mock
    private ChatRoomRepo roomRepo;

    @Mock
    private UserRepo userRepo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // ✅ Mock Security Context
        Authentication authentication = mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn("test@example.com");

        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);

        SecurityContextHolder.setContext(securityContext);
    }

    // -------------------- ADD MESSAGE -------------------- //

    @Test
    void testAddMessage_Success() {
        ChatMessageDto dto = new ChatMessageDto();
        dto.setRoomId(1);
        dto.setMessage("Hello");

        User user = new User();
        user.setId(1);
        user.setName("John");

        ChatRoom room = new ChatRoom();
        room.setId(1);

        ChatMessage savedMsg = new ChatMessage();
        savedMsg.setId(100);
        savedMsg.setMessage("Hello");
        savedMsg.setUser(user);
        savedMsg.setRoom(room);
        savedMsg.setCreatedAt(LocalDateTime.now());

        when(userRepo.findByEmail("test@example.com")).thenReturn(user);
        when(roomRepo.findById(1)).thenReturn(Optional.of(room));
        when(msgRepo.save(any(ChatMessage.class))).thenReturn(savedMsg);

        ChatMessageDto result = messageService.addMessage(dto);

        assertNotNull(result);
        assertEquals("Hello", result.getMessage());
        assertEquals(1, result.getRoomId());
        assertEquals("John", result.getUserName());

        verify(msgRepo).save(any(ChatMessage.class));
    }

    @Test
    void testAddMessage_RoomNotFound() {
        ChatMessageDto dto = new ChatMessageDto();
        dto.setRoomId(1);

        when(userRepo.findByEmail("test@example.com")).thenReturn(new User());
        when(roomRepo.findById(1)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            messageService.addMessage(dto);
        });

        assertEquals("Room not found", exception.getMessage());
    }

    // -------------------- GET MESSAGES -------------------- //

    @Test
    void testGetMessagesByRoom() {
        User user = new User();
        user.setName("John");

        ChatRoom room = new ChatRoom();
        room.setId(1);

        ChatMessage msg1 = new ChatMessage();
        msg1.setId(1);
        msg1.setMessage("Hi");
        msg1.setUser(user);
        msg1.setRoom(room);
        msg1.setCreatedAt(LocalDateTime.now());

        ChatMessage msg2 = new ChatMessage();
        msg2.setId(2);
        msg2.setMessage("Hello");
        msg2.setUser(user);
        msg2.setRoom(room);
        msg2.setCreatedAt(LocalDateTime.now());

        when(msgRepo.findByRoomIdOrderByCreatedAtAsc(1))
                .thenReturn(List.of(msg1, msg2));

        List<ChatMessageDto> result = messageService.getMessagesByRoom(1);

        assertEquals(2, result.size());
        assertEquals("Hi", result.get(0).getMessage());
        assertEquals("Hello", result.get(1).getMessage());
    }
}