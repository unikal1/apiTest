package com.apitest.note.api;

import com.apitest.apiTestManage.dto.SuccessResponseDto;
import com.apitest.note.Note;
import com.apitest.note.config.CommonUtil;
import com.apitest.note.dto.NoteGetDto;
import com.apitest.note.dto.NoteSaveDto;
import com.apitest.note.service.NoteService;
import groovy.lang.DelegatesTo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <br>package name   : com.apitest.note.api
 * <br>file name      : NoteController
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

@Controller
@RequiredArgsConstructor
public class NoteController {

    private final CommonUtil commonUtil;
    private final NoteService noteService;

    @GetMapping("/note/create")
    public String createNote() {
        return "note/note_create";
    }

    @PostMapping("/note/save")
    public ResponseEntity<SuccessResponseDto> saveNote(@RequestBody NoteSaveDto dto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username=  authentication.getName();

        noteService.saveNote(username, dto);

        return ResponseEntity.ok(new SuccessResponseDto("success"));

    }

    @GetMapping("/note/list")
    public String getNoteList(Model model,
                              @RequestParam(defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 20, Sort.by(Sort.Direction.DESC, "createdDate"));
        Page<NoteGetDto> notes = noteService.getNotes(pageable);
        List<String> tags = noteService.getTags();

        model.addAttribute("notes", notes);
        model.addAttribute("tags", tags);
        return "note/noteList";
    }

    @GetMapping("/note/search")
    public String searchNotes(@RequestParam(required = false) String title, @RequestParam(required = false) String tag, @RequestParam(defaultValue = "0") int page, Model model) {
        Pageable pageable = PageRequest.of(page, 20, Sort.by(Sort.Direction.DESC, "createdDate"));
        Page<NoteGetDto> notes;

        if (title != null) {
            notes = noteService.searchNotesByTitle(title, pageable);
        } else if (tag != null) {
            notes = noteService.getNotesByTag(tag, pageable);
        } else {
            notes = noteService.getNotes(pageable);
        }

        List<String> tags = noteService.getTags();
        model.addAttribute("notes", notes);
        model.addAttribute("tags", tags);
        return "note/noteList";
    }

    @GetMapping("/note/get")
    public String viewNote(@RequestParam("id") Long id, Model model) {
        Note note = noteService.findById(id);
        String contentHtml = commonUtil.markdownToHtml(note.getContent());
        model.addAttribute("title", note.getTitle());
        model.addAttribute("tags", note.getTags());
        model.addAttribute("username", note.getAuthor().getUsername());
        model.addAttribute("content", contentHtml);
        model.addAttribute("noteId", id);
        return "note/note_view";
    }

    @GetMapping("/note/update")
    public String updateNoteForm(@RequestParam Long id, Model model) {
        Note note = noteService.findById(id);
        model.addAttribute("title", note.getTitle());
        model.addAttribute("tags", note.getTags());
        model.addAttribute("content", note.getContent());
        model.addAttribute("noteId", id);
        return "note/note_update";
    }

    @PostMapping("/note/update")
    public ResponseEntity<SuccessResponseDto> updateNote(@RequestParam Long id, @RequestBody NoteSaveDto dto) {
        noteService.updateNote(id, dto);
        System.out.println(dto.getContent());
        return ResponseEntity.ok(new SuccessResponseDto("success"));
    }

    @GetMapping("/note/delete")
    public String deleteNoteConfirm(@RequestParam("id") Long id, Model model) {
        model.addAttribute("noteId", id);
        return "delete_page";
    }

    @PostMapping("/note/delete")
    public String deleteNote(@RequestParam("id") Long id) {
        noteService.deleteNoteById(id);
        return "redirect:/note/list";
    }

}
