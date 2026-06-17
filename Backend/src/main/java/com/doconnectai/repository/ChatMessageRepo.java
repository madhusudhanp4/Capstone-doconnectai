package com.doconnectai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doconnectai.entity.ChatMessage;

public interface ChatMessageRepo extends JpaRepository< ChatMessage, Integer> {

List<ChatMessage> findByRoomIdOrderByCreatedAtAsc( Integer roomId);


}