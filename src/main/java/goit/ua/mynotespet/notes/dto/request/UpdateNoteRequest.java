package goit.ua.mynotespet.notes.dto.request;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateNoteRequest {
    @Size(min = 3, max = 250, message = "Title must be between 3 and 250 characters")
    private String title;
    @Size(max = 10000, message = "Content cannot exceed 10000 characters")
    private String content;
}
