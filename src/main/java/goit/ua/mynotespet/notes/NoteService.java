package goit.ua.mynotespet.notes;

import goit.ua.mynotespet.notes.dto.request.CreateNoteRequest;
import goit.ua.mynotespet.notes.dto.request.UpdateNoteRequest;
import goit.ua.mynotespet.notes.dto.response.NoteResponse;
import goit.ua.mynotespet.users.User;
import goit.ua.mynotespet.users.UserService;
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

    public NoteResponse getNoteById(Long id) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Note not found! with id: " + id));

        return mapToResponse(note);
    }

    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    @Transactional
    public NoteResponse updateNote(UpdateNoteRequest request, Long id) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Note not found! with id: " + id));

        note.setTitle(request.getTitle());
        note.setContent(request.getContent());
        Note updateNote = noteRepository.save(note);
        return mapToResponse(updateNote);
    }

    public void deleteNoteById(Long id) {
        noteRepository.deleteById(id);
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
