package com.apitest.note.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <br>package name   : com.apitest.note.dto
 * <br>file name      : NoteGetDto
 * <br>date           : 2024-08-26
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
 * 2024-08-26        jack8              init create
 * </pre>
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NoteGetDto {
    private Long id;
    private String username;
    private String title;
    private List<String> tags;
    private String content;
    private LocalDateTime createdDate;
}
