package goit.ua.mynotespet.notes.service;

import goit.ua.mynotespet.notes.dto.request.CreateNoteRequest;
import goit.ua.mynotespet.notes.dto.request.UpdateNoteRequest;
import goit.ua.mynotespet.notes.dto.response.NoteResponse;
import goit.ua.mynotespet.notes.entity.Note;
import goit.ua.mynotespet.notes.exception.NoteNotFoundException;
import goit.ua.mynotespet.notes.repository.NoteRepository;
import goit.ua.mynotespet.users.entity.User;
import goit.ua.mynotespet.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;
    private final UserService userService;

    public NoteResponse createNote(CreateNoteRequest request, String username) {

        User user = userService.findByUsername(username);
        Note savedNote = noteRepository.save(Note.builder()
                .user(user)
                .title(request.getTitle())
                .content(request.getContent())
                .createdAt(Instant.now())
                .build());
        return mapToResponse(savedNote);
    }

    public NoteResponse getNoteById(Long id, String username) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new NoteNotFoundException("Note not found! with id: " + id));
        if (!note.getUser().getUsername().equals(username)) {
            throw new NoteNotFoundException("Note not found!");
        }
        return mapToResponse(note);
    }

    public List<NoteResponse> getAllNotes(String username) {
        return noteRepository.findAllByUserUsername(username)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Transactional
    public NoteResponse updateNote(Long id, UpdateNoteRequest request, String username) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new NoteNotFoundException("Note not found! with id: " + id));

        if (!note.getUser().getUsername().equals(username)) {
            throw new NoteNotFoundException("Note not found!");
        }
        if (request.getTitle() != null && !request.getTitle().equals(note.getTitle())) {
            note.setTitle(request.getTitle());
        }
        if (request.getContent() != null && !request.getContent().equals(note.getContent())) {
            note.setContent(request.getContent());
        }

        Note updateNote = noteRepository.save(note);
        return mapToResponse(updateNote);

    }

    public void deleteNoteById(Long id, String username) {
        Note note = noteRepository.findById(id).orElseThrow(() -> new NoteNotFoundException("Note not found! with id: " + id));
        if (!note.getUser().getUsername().equals(username)) {
            throw new NoteNotFoundException("Note not found!");
        }
        noteRepository.delete(note);
    }

    private NoteResponse mapToResponse(Note note) {
        return NoteResponse.builder()
                .id(note.getId())
                .title(note.getTitle())
                .content(note.getContent())
                .createdAt(note.getCreatedAt())
                .build();
    }
}
