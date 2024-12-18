package com.example.websocket.server.chat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ChatController {

    @MessageMapping("/sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        log.info("Message sent: {}", chatMessage.toString());
        return chatMessage;
    }

    @MessageMapping("/addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        // Add username in websocket session
        log.info("User added: {}", chatMessage.toString());
        headerAccessor
            .getSessionAttributes()
            .put("username", chatMessage.getSender());
        return chatMessage;
    }
}
