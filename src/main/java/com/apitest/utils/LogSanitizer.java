package com.apitest.utils;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

/**
 * <br>package name   : com.apitest.utils
 * <br>file name      : LogSanitizer
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
@Component
public class LogSanitizer {
    private final Pattern ANSI_PATTERN = Pattern.compile("\\u001B\\[[;\\d]*m");

    public String sanitize(String log) {
        // ANSI 이스케이프 시퀀스 제거
        return ANSI_PATTERN.matcher(log).replaceAll("");
    }
}
