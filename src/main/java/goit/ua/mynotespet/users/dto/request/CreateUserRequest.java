package goit.ua.mynotespet.users.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {

    @NotBlank(message = "Username doesn't exist")
    @Size(min = 3, max = 50, message = "must be between 3 and 50 characters")
    private String username;
    @NotBlank(message = "password doesn't exist")
    @Size(min = 8, max = 60, message = "must be between 8 and 60 characters")
    private String password;
    @NotBlank(message = "email doesn't exist")
    @Size(min = 3, max = 254, message = "must be between 3 and 254 characters")
    @Email(message = "email must be valid")
    private String email;
}
