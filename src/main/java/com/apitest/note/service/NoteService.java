package com.apitest.note.service;

import com.apitest.member.Member;
import com.apitest.member.dao.MemberRepository;
import com.apitest.note.Note;
import com.apitest.note.dao.NoteRepository;
import com.apitest.note.dto.NoteGetDto;
import com.apitest.note.dto.NoteSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * <br>package name   : com.apitest.note.service
 * <br>file name      : NoteService
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

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;
    private final MemberRepository memberRepository;

    public void saveNote(String username, NoteSaveDto dto) {
        Member member = memberRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("unknown username"));

        Note note = Note.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .author(member)
                .tags(dto.getTags())
                .build();

        noteRepository.save(note);
    }

    public List<String> getTags() {
        return noteRepository.findDistinctTags();
    }

    public Page<NoteGetDto> getNotesByTag(String tag, Pageable pageable) {
        return noteRepository.findByTagsContaining(tag, pageable)
                .map(this::convertToNoteGetDto);
    }

    public Page<NoteGetDto> getNotes(Pageable pageable) {
        return noteRepository.findAll(pageable)
                .map(this::convertToNoteGetDto);
    }

    public Page<NoteGetDto> searchNotesByTitle(String title, Pageable pageable) {
        return noteRepository.findByTitleContaining(title, pageable).map(this::convertToNoteGetDto);
    }

    private NoteGetDto convertToNoteGetDto(Note note) {
        return NoteGetDto.builder()
                .id(note.getId())
                .username(note.getAuthor().getUsername())
                .title(note.getTitle())
                .tags(note.getTags())
                .content(getFirst25CharsExcludingSpecialChars(note.getContent()))
                .createdDate(note.getCreatedDate())
                .build();
    }

    private String getFirst25CharsExcludingSpecialChars(String content) {
        if (content == null) {
            return "";
        }

        // #, - 등을 제외하고 25글자를 추출
        String filteredContent = content.replaceAll("[#\\-]", "");

        // 필요한 경우, 25글자로 잘라냅니다.
        return filteredContent.length() > 25 ? filteredContent.substring(0, 25) : filteredContent;
    }

    public Note findById(Long id) {

        return noteRepository.findById(id).orElseThrow(() -> new NoSuchElementException("unknown note"));
    }

    public void updateNote(Long id, NoteSaveDto dto) {
        Note note = noteRepository.findById(id).orElseThrow(() -> new NoSuchElementException("unknown note"));


        note.setTags(dto.getTags());
        note.setTitle(dto.getTitle());
        note.setContent(dto.getContent());

        noteRepository.save(note);
    }

    public void deleteNoteById(Long id) {
        noteRepository.deleteById(id);
    }

}
