package library.controller;

import java.util.List;
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

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.validation.Valid;
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
    @JsonView(Book.PublicView.class)
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(books);
    }

    @GetMapping("/{id}")
    @JsonView(Book.PublicView.class)
    public ResponseEntity<Book> getBook(@PathVariable long id) {
        Book book = bookService.getBook(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(book);
    }

    @PostMapping("")
    public ResponseEntity<Void> createBook(@Valid @RequestBody Book newBook) {
        bookService.createBook(newBook);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateBook(@PathVariable long id, @RequestBody Book book) {
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

    @PostMapping("/{bookId}/author/{authorId}")
    public ResponseEntity<Void> linkBookAndAuthor(
            @PathVariable long bookId,
            @PathVariable long authorId) {

        bookService.linkBookAndAuthor(bookId, authorId);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @DeleteMapping("/{bookId}/author/{authorId}")
    public ResponseEntity<Void> unlinkBookAndAuthor(
            @PathVariable long bookId,
            @PathVariable long authorId) {

        bookService.unlinkBookAndAuthor(bookId, authorId);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
