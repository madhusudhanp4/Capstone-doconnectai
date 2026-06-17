package com.doconnectai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doconnectai.entity.ChatRoom;

public interface ChatRoomRepo
extends JpaRepository<
ChatRoom,
Integer> {
}