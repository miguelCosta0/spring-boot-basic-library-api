package library.DTO;

import java.time.LocalDate;
import library.model.Author;

public record AuthorResponse(
    Long id,
    String name,
    String cpf,
    LocalDate dateOfBirth) {

    public static AuthorResponse fromAuthor(Author author) {
        return new AuthorResponse(
            author.getId(),
            author.getName(),
            author.getCpf(),
            author.getDateOfBirth());
    }

}
