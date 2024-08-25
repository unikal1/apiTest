package com.apitest.apiTestManage.dto;

import groovy.transform.builder.Builder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * <br>package name   : com.apitest.apiTestManage.dto
 * <br>file name      : ApiRequestDto
 * <br>date           : 2024-08-25
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
 * 2024-08-25        jack8              init create
 * </pre>
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiRequestDto {
    @NotBlank(message = "API type is required.")
    private String apiType;

    @NotBlank(message = "Title is required.")
    private String title;

    @NotBlank(message = "URL is required.")
    private String url;

    @NotBlank(message = "Method is required.")
    private String method;

    private boolean isAuth;

    private String body;

    private String expectedResponse;

    private String note;

    @NotNull(message = "Validate info is required.")
    private Map<String, String> validateInfo;
}
