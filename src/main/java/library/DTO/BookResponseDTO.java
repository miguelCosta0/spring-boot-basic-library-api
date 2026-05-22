package library.DTO;

import library.model.Book;

public record BookResponseDTO(
    Long id,
    String title,
    String description) {

    public static BookResponseDTO from(Book book) {
        return new BookResponseDTO(
            book.getId(),
            book.getTitle(),
            book.getDescription());
    }

}
