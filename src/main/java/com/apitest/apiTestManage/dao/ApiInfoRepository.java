package com.apitest.apiTestManage.dao;

import com.apitest.apiTestManage.entity.ApiInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * <br>package name   : com.apitest.apiTestManage.dao
 * <br>file name      : ApiInfoRepository
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

@Repository
public interface ApiInfoRepository extends JpaRepository<ApiInfo,Long> {
    Optional<ApiInfo> findByTitle(String title);
}
