package goit.ua.mynotespet.error;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private List<String> message;
    private String error;
    private HttpStatus status;
    private Instant timestamp;
}
