package com.apitest.terminal.service;

import com.apitest.terminal.dto.ResourceData;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jcraft.jsch.Session;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * <br>package name   : com.apitest.terminal.service
 * <br>file name      : ResourceMonitorService
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
public class ResourceMonitorService {

    private final SSHConnectUtil sshConnectUtil;
    private final ObjectMapper objectMapper;
    public ResourceData getServerResources(String address) throws Exception {
        String host = "192.168.219.";
        int port = 22;
        String username = "sang";
        String password = "m7128226";
        String command = "python3 ~/resource_monitor.py";
        host = host + address;

        Session session = sshConnectUtil.createSession(username, password, host, port);
        try {
            // 명령어 실행
            String response = sshConnectUtil.executeCommand(session, command);
            System.out.println(response);
            ResourceData data=  objectMapper.readValue(response, ResourceData.class);
            System.out.println( data);
            return data;
        } finally {
            // SSH 세션 종료
            sshConnectUtil.disconnectSession(session);
        }
    }


}
