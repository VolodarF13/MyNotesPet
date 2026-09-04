package goit.ua.mynotespet.notes.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateNoteResponse {
    private UUID id;
    private String title;
    private String content;
    private Instant createdAt;
}
