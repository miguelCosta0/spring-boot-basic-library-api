package library.DTO;

import library.model.Book;

public record BookResponse(
    Long id,
    String title,
    String description) {

    public static BookResponse fromBook(Book book) {
        return new BookResponse(
            book.getId(),
            book.getTitle(),
            book.getDescription());
    }

}
