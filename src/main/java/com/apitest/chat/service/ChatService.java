package com.apitest.chat.service;

import com.apitest.chat.dao.ChatMessageRepository;
import com.apitest.chat.entity.ChatMessage;
import com.apitest.chat.entity.ChatMessageEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <br>package name   : com.apitest.chat.service
 * <br>file name      : ChatService
 * <br>date           : 2024-08-27
 * <pre>
 * <span style="color: white;">[description]</span>
 *
 * </pre>
 * <pre>
 * <span style="color: white;">usage:</span>
 * {@code
 *
 * } </pre>
 * <pre>
 * modified log :
 * ====================================================
 * DATE           AUTHOR               NOTE
 * ----------------------------------------------------
 * 2024-08-27        jack8              init create
 * </pre>
 */

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatMessageRepository chatMessageRepository;

    public ChatMessageEntity saveMessage(ChatMessage chatMessage) {
        ChatMessageEntity saved = ChatMessageEntity.builder()
                .type(chatMessage.getType())
                .content(chatMessage.getContent())
                .sender(chatMessage.getSender())
                .build();
        return chatMessageRepository.save(saved);
    }

    public List<ChatMessage> getAllMessages() {
        return chatMessageRepository.findAll().stream().map(this::convertToChatMessage).toList();
    }

    private ChatMessage convertToChatMessage(ChatMessageEntity entity) {
        return ChatMessage.builder()
                .content(entity.getContent())
                .sender(entity.getSender())
                .type(entity.getType())
                .build();
    }
}
