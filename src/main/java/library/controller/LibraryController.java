package library.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import library.service.LibraryService;

@Controller
@RequestMapping("/api/library")
public class LibraryController {

    private final LibraryService libraryService;

    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @PostMapping("/link-author-and-book")
    public ResponseEntity<Void> linkBookAndAuthor(
        @RequestParam long bookId,
        @RequestParam long authorId) {
        libraryService.linkBookAndAuthor(bookId, authorId);

        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build();
    }

    @DeleteMapping("/unlink-author-and-book")
    public ResponseEntity<Void> unlinkBookAndAuthor(
        @RequestParam long bookId,
        @RequestParam long authorId) {
        libraryService.unlinkBookAndAuthor(bookId, authorId);

        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build();
    }
}
