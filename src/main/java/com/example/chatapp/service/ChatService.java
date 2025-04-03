package com.example.chatapp.service;

import com.example.chatapp.dto.ChatMessageDto;
import com.example.chatapp.model.ChatMessage;
import com.example.chatapp.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatMessageRepository chatMessageRepository;

    public void saveMessage(ChatMessageDto dto) {
        ChatMessage message = ChatMessage.builder()
                .senderId(dto.getSenderId())
                .receiverId(dto.getReceiverId())
                .content(dto.getContent())
                .timestamp(LocalDateTime.now())
                .build();

        chatMessageRepository.save(message);
    }

    public List<ChatMessage> getChatHistory(String user1, String user2) {
        List<ChatMessage> messages1 = chatMessageRepository
                .findBySenderIdAndReceiverIdOrderByTimestampAsc(user1, user2);
        List<ChatMessage> messages2 = chatMessageRepository
                .findByReceiverIdAndSenderIdOrderByTimestampAsc(user1, user2);

        List<ChatMessage> fullHistory = new ArrayList<>();
        fullHistory.addAll(messages1);
        fullHistory.addAll(messages2);

        fullHistory.sort(Comparator.comparing(ChatMessage::getTimestamp));
        return fullHistory;
    }

}