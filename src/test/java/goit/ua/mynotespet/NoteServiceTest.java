package goit.ua.mynotespet;

import goit.ua.mynotespet.notes.Note;
import goit.ua.mynotespet.notes.NoteRepository;
import goit.ua.mynotespet.notes.NoteService;
import goit.ua.mynotespet.notes.dto.request.CreateNoteRequest;
import goit.ua.mynotespet.notes.dto.response.NoteResponse;
import goit.ua.mynotespet.users.User;
import goit.ua.mynotespet.users.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class NoteServiceTest {
    @Mock
    private NoteRepository noteRepository;
    @Mock
    private UserService userService;
    @InjectMocks
    private NoteService noteService;

    @Test
    @DisplayName("Should return NoteResponse when note exists by ID")
    void getNoteById_WhenNoteExists_ReturnNoteResponse() {
        Long noteId = 1L;
        Note mockNote = Note.builder()
                .id(noteId)
                .title("TestTitle")
                .content("TestContent")
                .createdAt(Instant.now())
                .build();

        when(noteRepository.findById(noteId)).thenReturn(Optional.of(mockNote));

        NoteResponse actualResponse = noteService.getNoteById(noteId);

        assertNotNull(actualResponse);
        assertEquals(mockNote.getId(), actualResponse.getId());
        assertEquals(mockNote.getTitle(), actualResponse.getTitle());
        assertEquals(mockNote.getContent(), actualResponse.getContent());

        verify(noteRepository, times(1)).findById(noteId);
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when note does not exist")
    void getNoteById_WhenNoteDoesNotExist_ThrowsException() {
        Long nonExistingID = 99L;

        when(noteRepository.findById(nonExistingID)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> noteService.getNoteById(nonExistingID)
        );

        assertEquals("Note not found! with id: " + nonExistingID, exception.getMessage());



        verify(noteRepository, times(1)).findById(nonExistingID);
    }

    @Test
    @DisplayName("Should create and return NoteResponse when request is valid")
    void createNote_WhenValidRequest_ReturnsNoteResponse(){
        String username = "john_doe";
        CreateNoteRequest request = new CreateNoteRequest("New Title", "New Content");

        User mockUser = User.builder()
                .id(1L)
                .username(username)
                .build();

        Note savedNote = Note.builder()
                .id(10L)
                .title(request.getTitle())
                .content(request.getContent())
                .user(mockUser)
                .createdAt(Instant.now())
                .build();

        when(userService.findByUsername(username)).thenReturn(mockUser);

        when(noteRepository.save(any(Note.class))).thenReturn(savedNote);

        NoteResponse actualResponse = noteService.createNote(request, username);

        assertNotNull(actualResponse);
        assertEquals(savedNote.getId(), actualResponse.getId());
        assertEquals(request.getTitle(), actualResponse.getTitle());
        assertEquals(request.getContent(), actualResponse.getContent());

        verify(userService, times(1)).findByUsername(username);
        verify(noteRepository, times(1)).save(any(Note.class));
    }
}
