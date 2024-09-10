package com.apitest.chat.dao;

import com.apitest.chat.entity.ChatMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <br>package name   : com.apitest.chat.dao
 * <br>file name      : ChatMessageRepostiory
 * <br>date           : 2024-08-27
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
 * 2024-08-27        jack8              init create
 * </pre>
 */
@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessageEntity, Long> {
}
