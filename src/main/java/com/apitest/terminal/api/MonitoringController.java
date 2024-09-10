package com.apitest.terminal.api;

import com.apitest.terminal.dto.ResourceData;
import com.apitest.terminal.service.ResourceMonitorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <br>package name   : com.apitest.terminal.api
 * <br>file name      : MonitoringController
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
public class MonitoringController {

    private final ResourceMonitorService resourceMonitorService;

    @GetMapping("/terminal")
    public String getTerminalPage(@RequestParam String server, Model model) throws Exception {
        ResourceData resourceData = resourceMonitorService.getServerResources(server);
        model.addAttribute("resourceData", resourceData);
        String title = "server monitoring";
        switch (server) {
            case "101":
                title = "DevOps Server Monitoring";
                break;
            case "135":
                title = "Main Server Monitoring";
                break;
            case "144":
                title = "Minio Server Monitoring";
                break;
            default:
                break;
        }
        model.addAttribute("title", title);
        model.addAttribute("server", server);
        return "terminal/server_monitor";
    }

    @GetMapping("/api/monitor")
    @ResponseBody
    public ResourceData getResources(@RequestParam String server) throws Exception {
        return resourceMonitorService.getServerResources(server);
    }
}
