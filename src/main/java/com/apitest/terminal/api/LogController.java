package com.apitest.terminal.api;

import com.apitest.apiTestManage.api.RequestServer;
import com.apitest.apiTestManage.entity.ApiInfo;
import com.apitest.terminal.dto.LogRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Flux;

import java.util.Map;

/**
 * <br>package name   : com.apitest.terminal.api
 * <br>file name      : LogController
 * <br>date           : 2024-08-28
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
 * 2024-08-28        jack8              init create
 * </pre>
 */
@Controller
@RequiredArgsConstructor
public class LogController {

    private final SimpMessagingTemplate messagingTemplate;
    private final RequestServer requestServer;

    @MessageMapping("/logs")
    public void streamLogs(LogRequest logRequest) {
        String containerName = logRequest.getContainerName();
        String sessionId = logRequest.getSessionId();

        // 실시간 로그 스트리밍
        Flux<String> logStream = requestServer.streamLogs(containerName, sessionId );
        logStream.subscribe(log -> {
            // WebSocket을 통해 클라이언트에 전송
            System.out.println(log);
            messagingTemplate.convertAndSend("/topic/logs", log);
        });
    }

    @GetMapping("/api/log")
    public String getLogPage(Model model) {
        String defaultContainerName = "one-bucket-container"; // 예시로 기본 컨테이너 이름 설정
        model.addAttribute("containerName", defaultContainerName);
        return "terminal/log_viewer";
    }
}

