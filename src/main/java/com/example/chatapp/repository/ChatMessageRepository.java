package com.example.chatapp.repository;

import java.util.List;

import com.example.chatapp.model.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {

    List<ChatMessage> findBySenderIdAndReceiverIdOrderByTimestampAsc(String senderId, String receiverId);

    List<ChatMessage> findByReceiverIdAndSenderIdOrderByTimestampAsc(String receiverId, String senderId);


}
