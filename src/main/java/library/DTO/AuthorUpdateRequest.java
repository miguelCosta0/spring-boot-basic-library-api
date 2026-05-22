package library.DTO;

import java.time.LocalDate;

public record AuthorUpdateRequest(
    String name,
    String cpf,
    LocalDate dateOfBirth) {
}
