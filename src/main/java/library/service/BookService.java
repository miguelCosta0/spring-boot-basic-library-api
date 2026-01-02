package library.service;

import java.util.List;

import library.model.Book;

public interface BookService {

    public List<Book> getAllBooks();

    public Book getBook(long id);

    public void createBook(Book newBook);

    public void updateBook(long id, Book book);

    public void deleteBook(long id);

    public void linkBookAndAuthor(long bookId, long authorId);

    public void unlinkBookAndAuthor(long bookId, long authorId);

}
