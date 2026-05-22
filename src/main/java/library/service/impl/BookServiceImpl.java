package library.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import library.DTO.BookCreateRequest;
import library.DTO.BookUpdateRequest;
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
        List<Book> books = bookRepository.findAll();

        if (books.size() == 0)
            throw new NotFoundException("There are no books registered");

        return books;
    }

    @Override
    public Book getBook(long id) {
        var book = bookRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException("Book not found"));
        return book;
    }

    @Override
    public void createBook(BookCreateRequest bookDto) {
        var book = new Book(
            bookDto.title(),
            bookDto.description());

        book = bookRepository.saveAndFlush(book);

        if (book == null)
            throw new InternalServerException("Book could not be created");

    }

    @Override
    public void updateBook(long id, BookUpdateRequest bookDto) {
        var book = getBook(id);

        if (bookDto.title() != null)
            book.setTitle(bookDto.title());
        if (bookDto.description() != null)
            book.setDescription(bookDto.description());

        book = bookRepository.saveAndFlush(book);
        if (book == null)
            throw new InternalServerException("Book could not be updated");
    }

    @Override
    public void deleteBook(long id) {
        getBook(id);
        bookRepository.deleteById(id);
    }

}
