package library.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import jakarta.validation.Valid;

import library.DTO.BookRequestDTO;
import library.DTO.BookResponseDTO;
import library.model.Author;
import library.model.Book;
import library.service.BookService;

@Controller
@RequestMapping("/api/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("")
    public ResponseEntity<List<BookResponseDTO>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        List<BookResponseDTO> booksResponse = books
            .stream()
            .map(BookResponseDTO::from)
            .collect(Collectors.toList());

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(booksResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDTO> getBook(@PathVariable long id) {
        Book book = bookService.getBook(id);
        BookResponseDTO bookResponse = BookResponseDTO.from(book);

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(bookResponse);
    }

    @PostMapping("")
    public ResponseEntity<Void> createBook(@Valid @RequestBody BookRequestDTO newBook) {
        bookService.createBook(newBook);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateBook(@PathVariable long id,
        @Valid @RequestBody BookRequestDTO book) {
        bookService.updateBook(id, book);
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable long id) {
        bookService.deleteBook(id);
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build();
    }

    @GetMapping("/{id}/authors")
    public ResponseEntity<List<Long>> getBookAuthors(@PathVariable long id) {
        var book = bookService.getBook(id);
        List<Long> bookAuthorsIds = book
            .getAuthors()
            .stream()
            .map(Author::getId)
            .collect(Collectors.toList());

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(bookAuthorsIds);
    }
}
