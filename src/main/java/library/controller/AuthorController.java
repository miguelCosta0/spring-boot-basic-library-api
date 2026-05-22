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

import library.DTO.AuthorCreateRequest;
import library.DTO.AuthorResponse;
import library.DTO.AuthorUpdateRequest;
import library.model.Author;
import library.model.Book;
import library.service.AuthorService;

@Controller
@RequestMapping("/api/author")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("")
    public ResponseEntity<List<AuthorResponse>> getAllAuthors() {
        List<Author> authors = authorService.getAllAuthors();

        List<AuthorResponse> authorsResponse = authors
            .stream()
            .map(AuthorResponse::fromAuthor)
            .collect(Collectors.toList());

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(authorsResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponse> getAuthor(@PathVariable long id) {
        Author author = authorService.getAuthor(id);
        AuthorResponse authorResponse = AuthorResponse.fromAuthor(author);

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(authorResponse);
    }

    @PostMapping("")
    public ResponseEntity<Void> createAuthor(@Valid @RequestBody AuthorCreateRequest newAuthor) {
        authorService.createAuthor(newAuthor);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAuthor(
        @PathVariable long id,
        @Valid @RequestBody AuthorUpdateRequest author) {
        authorService.updateAuthor(id, author);
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable long id) {
        authorService.deleteAuthor(id);
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build();
    }

    @GetMapping("/{id}/books")
    public ResponseEntity<List<Long>> getAuthorBooks(@PathVariable long id) {
        var author = authorService.getAuthor(id);
        List<Long> authorBooksIds = author
            .getBooks()
            .stream()
            .map(Book::getId)
            .collect(Collectors.toList());

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(authorBooksIds);
    }
}
