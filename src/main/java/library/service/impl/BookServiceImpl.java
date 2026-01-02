package library.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import library.exception.InternalServerException;
import library.exception.NotFoundException;
import library.model.Book;
import library.repository.BookRepository;
import library.service.BookService;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = bookRepository.getAllBooks();

        if (books.size() == 0)
            throw new NotFoundException("There are no books registered");

        return books;
    }

    @Override
    public Book getBook(long id) {
        Optional<Book> book = bookRepository.getBook(id);
        return book
                .orElseThrow(() -> new NotFoundException("There is no book with such id"));
    }

    @Override
    public void createBook(Book newBook) {
        int res = bookRepository.createBook(newBook);

        if (res == 0)
            throw new InternalServerException("Book could not be created");

    }

    @Override
    public void updateBook(long id, Book book) {
        getBook(id);
        int res = bookRepository.updateBook(id, book);
        if (res == 0)
            throw new InternalServerException("Book could not be updated");
    }

    @Override
    public void deleteBook(long id) {
        getBook(id);
        int res = bookRepository.deleteBook(id);
        if (res == 0)
            throw new InternalServerException("Book could not be deleted");
    }

    @Override
    public void linkBookAndAuthor(long bookId, long authorId) {
        int res = bookRepository.linkBookAndAuthor(bookId, authorId);
        if (res == 0)
            throw new InternalServerException("Failed to map book-author relationship");

    }

    @Override
    public void unlinkBookAndAuthor(long bookId, long authorId) {
        int res = bookRepository.unlinkBookAndAuthor(bookId, authorId);
        if (res == 0)
            throw new InternalServerException("Failed to destroy book-author relationship");
    }
}
