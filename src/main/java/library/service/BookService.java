package library.service;

import java.util.List;
import library.DTO.BookCreateRequest;
import library.DTO.BookUpdateRequest;
import library.model.Book;

public interface BookService {

    public List<Book> getAllBooks();

    public Book getBook(long id);

    public void createBook(BookCreateRequest bookDto);

    public void updateBook(long id, BookUpdateRequest bookDto);

    public void deleteBook(long id);

}
