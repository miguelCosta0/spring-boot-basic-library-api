package library.service;

import java.util.List;
import library.DTO.BookRequestDTO;
import library.model.Book;

public interface BookService {

    public List<Book> getAllBooks();

    public Book getBook(long id);

    public void createBook(BookRequestDTO newBook);

    public void updateBook(long id, BookRequestDTO book);

    public void deleteBook(long id);

}
