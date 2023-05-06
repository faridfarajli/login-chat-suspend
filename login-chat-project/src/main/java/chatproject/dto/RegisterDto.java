package chatproject.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterDto {
    Long id;
    @NotEmpty
    String username;
    @NotEmpty
    String email;
    @NotEmpty
    String password;

}
