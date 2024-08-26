package com.apitest.note.dao;

import com.apitest.note.Note;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import reactor.util.annotation.NonNull;

import java.util.List;

/**
 * <br>package name   : com.apitest.note.dao
 * <br>file name      : NoteRepository
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
@Repository
public interface NoteRepository extends JpaRepository<Note,Long> {

    @Query("SELECT DISTINCT t FROM Note n JOIN n.tags t")
    List<String> findDistinctTags();

    Page<Note> findByTitleContaining(String title, Pageable pageable);

    Page<Note> findByTagsContaining(String tag, Pageable pageable);


    @NonNull
    Page<Note> findAll(@NonNull Pageable pageable);

}
