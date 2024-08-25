package com.apitest.apiTestManage.dto;

import groovy.transform.builder.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <br>package name   : com.apitest.apiTestManage.dto
 * <br>file name      : SuccessResponseDto
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
public class SuccessResponseDto {
    private String message;
}
