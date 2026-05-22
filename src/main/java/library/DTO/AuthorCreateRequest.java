package library.DTO;

import java.time.LocalDate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

public record AuthorCreateRequest(
    @NotBlank String name,
    @NotBlank String cpf,
    @NotNull @Past LocalDate dateOfBirth) {
}
