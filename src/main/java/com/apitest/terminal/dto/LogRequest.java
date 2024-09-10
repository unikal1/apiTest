package com.apitest.terminal.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * <br>package name   : com.apitest.terminal.dto
 * <br>file name      : LogRequest
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

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LogRequest {
    private String sessionId;
    private String containerName;
}
