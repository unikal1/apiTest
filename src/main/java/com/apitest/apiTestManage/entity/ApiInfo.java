package com.apitest.apiTestManage.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.web.header.Header;

import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.util.ArrayList;
import java.util.List;

/**
 * <br>package name   : com.apitest.apiTestManage
 * <br>file name      : ApiInfo
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

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String type;

    private String title;
    private String url;
    private String method;
    private boolean isAuth;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String body;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Headers> headersList = new ArrayList<>();

    @Lob
    @Column(columnDefinition = "TEXT")
    private String expectedResponse;

    @Column(columnDefinition = "TEXT")
    private String note;
}
