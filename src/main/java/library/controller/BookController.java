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

import library.DTO.BookCreateRequest;
import library.DTO.BookResponse;
import library.DTO.BookUpdateRequest;
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
    public ResponseEntity<List<BookResponse>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        List<BookResponse> booksResponse = books
            .stream()
            .map(BookResponse::fromBook)
            .collect(Collectors.toList());

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(booksResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBook(@PathVariable long id) {
        Book book = bookService.getBook(id);
        BookResponse bookResponse = BookResponse.fromBook(book);

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(bookResponse);
    }

    @PostMapping("")
    public ResponseEntity<Void> createBook(@Valid @RequestBody BookCreateRequest newBook) {
        bookService.createBook(newBook);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateBook(
        @PathVariable long id,
        @Valid @RequestBody BookUpdateRequest bookDto) {
        bookService.updateBook(id, bookDto);
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
