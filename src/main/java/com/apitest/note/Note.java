package com.apitest.note;

import com.apitest.member.Member;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <br>package name   : com.apitest.note
 * <br>file name      : Note
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
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Member author;

    @ElementCollection(fetch=FetchType.EAGER)
    private List<String> tags = new ArrayList<>();

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @CreatedDate
    private LocalDateTime createdDate;

}
