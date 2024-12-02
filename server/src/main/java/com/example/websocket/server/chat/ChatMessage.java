package com.example.websocket.server.chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessage {
    private String content;
    private String sender;
    private MessageType type;

    public String toString() {
        return "ChatMessage{" +
                "content='" + content +
                ", sender='" + sender +
                ", type=" + type +
                '}';
    }
}
