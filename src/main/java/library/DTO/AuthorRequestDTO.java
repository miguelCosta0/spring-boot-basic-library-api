package library.DTO;

import java.time.LocalDate;

public record AuthorRequestDTO(
    String name,
    String cpf,
    LocalDate dateOfBirth) {
}
