package library.service.impl;

import org.springframework.stereotype.Service;
import library.exception.NotFoundException;
import library.repository.AuthorRepository;
import library.repository.BookRepository;
import library.service.LibraryService;

@Service
public class LibraryServiceImpl implements LibraryService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public LibraryServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public void linkBookAndAuthor(long bookId, long authorId) {
        var book = bookRepository
            .findById(bookId)
            .orElseThrow(() -> new NotFoundException("Book not found"));
        var author = authorRepository
            .findById(authorId)
            .orElseThrow(() -> new NotFoundException("Author not found"));

        author.addBook(book);
        book.addAuthor(author);

        authorRepository.saveAndFlush(author);
    }

    @Override
    public void unlinkBookAndAuthor(long bookId, long authorId) {
        var book = bookRepository
            .findById(bookId)
            .orElseThrow(() -> new NotFoundException("Book not found"));
        var author = authorRepository
            .findById(authorId)
            .orElseThrow(() -> new NotFoundException("Author not found"));

        author.removeBook(book);
        book.removeAuthor(author);

        authorRepository.saveAndFlush(author);
    }

}
