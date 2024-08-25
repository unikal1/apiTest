package com.apitest.member.dto;

import lombok.*;

/**
 * <br>package name   : com.apitest.member.dto
 * <br>file name      : SignInRequestDto
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

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequestDto {

    private String username;
    private String password;
    private String email;
    private String name;
}
