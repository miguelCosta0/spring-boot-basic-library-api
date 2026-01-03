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

import library.model.Author;
import library.service.AuthorService;

@Controller
@RequestMapping("/api/author")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("")
    @JsonView(Author.PublicView.class)
    public ResponseEntity<List<Author>> getAllAuthors() {
        List<Author> authors = authorService.getAllAuthors();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(authors);
    }

    @GetMapping("/{id}")
    @JsonView(Author.PublicView.class)
    public ResponseEntity<Author> getAuthor(@PathVariable long id) {
        Author author = authorService.getAuthor(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(author);
    }

    @PostMapping("")
    public ResponseEntity<Void> createAuthor(@Valid @RequestBody Author newAuthor) {
        authorService.createAuthor(newAuthor);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAuthor(@PathVariable long id,
            @Valid @RequestBody Author author) {
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

}
