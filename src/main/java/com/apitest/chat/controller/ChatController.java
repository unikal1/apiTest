package com.apitest.chat.controller;

import com.apitest.chat.entity.ChatMessage;
import com.apitest.chat.entity.ChatMessageEntity;
import com.apitest.chat.service.ChatService;
import com.apitest.member.dao.MemberRepository;
import com.apitest.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Objects;

/**
 * <br>package name   : com.apitest.chat.controller
 * <br>file name      : ChatController
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

@Controller
@RequiredArgsConstructor
public class ChatController {
    private final MemberService memberService;
    private final ChatService chatService;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(ChatMessage chatMessage) {
        chatService.saveMessage(chatMessage);
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username=  authentication.getName();

        String name = memberService.getName(username);

        Objects.requireNonNull(headerAccessor.getSessionAttributes()).put(name, chatMessage.getSender());
        return chatMessage;
    }

    @GetMapping("/chat")
    public String chat(Model model) {
        // 현재 인증된 사용자 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // 사용자 이름 가져오기

        String name = memberService.getName(username);

        List<ChatMessage> chatMessages = chatService.getAllMessages();

        // 모델에 사용자 이름 추가
        model.addAttribute("username", name);
        model.addAttribute("chatMessages", chatMessages);
        return "chat"; // chat.html 뷰 반환
    }
}
