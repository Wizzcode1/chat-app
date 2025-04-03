package com.example.chatapp.controller;

import java.util.List;

import com.example.chatapp.dto.ChatMessageDto;
import com.example.chatapp.model.ChatMessage;
import com.example.chatapp.service.ChatService;
import lombok.RequiredArgsConstructor;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chat")
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatService chatService;

    @MessageMapping("/send")
    public void sendMessage(ChatMessageDto messageDto) {
        chatService.saveMessage(messageDto);
        messagingTemplate.convertAndSend("/topic/messages/" + messageDto.getReceiverId(), messageDto);
    }

    @GetMapping("/history")
    public List<ChatMessage> getChatHistory(
            @RequestParam String user1,
            @RequestParam String user2) {
        return chatService.getChatHistory(user1, user2);
    }
}