package com.apitest.apiTestManage.dto;

import lombok.*;


/**
 * <br>package name   : com.apitest.apiTestManage.dto
 * <br>file name      : ApiDetailsDto
 * <br>date           : 2024-08-24
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
 * 2024-08-24        jack8              init create
 * </pre>
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiDetailsDto {
    private String id;
    private String title;
    private String url;
    private String method;
    private boolean isAuth;
    private String body;
    private String expectedResponse;
    private String note;
}
