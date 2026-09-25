package goit.ua.mynotespet.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ErrorResponse.builder()
                        .message(List.of(ex.getMessage()))
                        .error("Not Found")
                        .status(HttpStatus.NOT_FOUND)
                        .timestamp(Instant.now())
                        .build());
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleAlreadyExists(AlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                ErrorResponse.builder()
                        .message(List.of(ex.getMessage()))
                        .error("Already Exists")
                        .status(HttpStatus.CONFLICT)
                        .timestamp(Instant.now())
                        .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public  ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ErrorResponse.builder()
                        .message(ex.getBindingResult().getFieldErrors()
                                .stream()
                                .map(FieldError::getDefaultMessage)
                                .toList()
                        )
                        .error("BAD REQUEST")
                        .status(HttpStatus.BAD_REQUEST)
                        .timestamp(Instant.now())
                        .build());

    }
}
