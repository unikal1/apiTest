package com.apitest.apiTestManage.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * <br>package name   : com.apitest.apiTestManage.entity
 * <br>file name      : Headers
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
@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Headers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String headerKey;
    private String value;
}
