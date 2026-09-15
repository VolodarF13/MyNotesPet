package goit.ua.mynotespet.notes.controller;

import goit.ua.mynotespet.notes.NoteService;
import goit.ua.mynotespet.notes.dto.request.CreateNoteRequest;
import goit.ua.mynotespet.notes.dto.request.UpdateNoteRequest;
import goit.ua.mynotespet.notes.dto.response.NoteResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @GetMapping
    public List<NoteResponse> getAllNotes(@RequestParam String username) {
        return noteService.getAllNotes(username);
    }

    @GetMapping("/{id}")
    public NoteResponse getNoteById(@PathVariable Long id) {
        return noteService.getNoteById(id);
    }

    @PostMapping
    public NoteResponse createNote(@RequestBody @Valid CreateNoteRequest request, @RequestParam String username) {
        return noteService.createNote(request, username);
    }

    @DeleteMapping("/{id}")
    public void deleteNoteById(@PathVariable Long id) {
        noteService.deleteNoteById(id);
    }

    @PatchMapping("/{id}")
    public NoteResponse updateNoteById(@RequestBody @Valid UpdateNoteRequest request, @PathVariable Long id) {
        return noteService.updateNote(request, id);
    }
}
