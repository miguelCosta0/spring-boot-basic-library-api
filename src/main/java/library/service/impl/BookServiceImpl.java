package library.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;

import library.DTO.BookRequestDTO;
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
    public void createBook(BookRequestDTO newBook) {
        var book = new Book(
            newBook.title(),
            newBook.description());

        book = bookRepository.saveAndFlush(book);

        if (book == null)
            throw new InternalServerException("Book could not be created");

    }

    @Override
    public void updateBook(long id, BookRequestDTO bookReq) {
        var book = getBook(id);
        book.setTitle(bookReq.title());
        book.setDescription(bookReq.description());

        book = bookRepository.saveAndFlush(book);
        if (book == null)
            throw new InternalServerException("Book could not be updated");
    }

    @Override
    public void deleteBook(long id) {
        bookRepository.deleteById(id);
    }

}
