package library.DTO;

import java.time.LocalDate;
import library.model.Author;

public record AuthorResponseDTO(
    Long id,
    String name,
    String cpf,
    LocalDate dateOfBirth) {

    public static AuthorResponseDTO from(Author author) {
        return new AuthorResponseDTO(
            author.getId(),
            author.getName(),
            author.getCpf(),
            author.getDateOfBirth());
    }

}
