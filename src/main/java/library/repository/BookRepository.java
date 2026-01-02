package library.repository;

import java.util.List;
import java.util.Optional;

import library.model.Book;

public interface BookRepository {

    /**
     * @return the Book (if exists)
     */
    public Optional<Book> getBook(long id);

    /**
     * @return a List of all registered books
     */
    public List<Book> getAllBooks();

    /**
     * @return number of affected rows
     */
    public int createBook(Book book);

    /**
     * @return number of affected rows
     */
    public int updateBook(long id, Book newBook);

    /**
     * @return number of affected rows
     */
    public int deleteBook(long id);

    /**
     * @return number of affected rows
     */
    public int linkBookAndAuthor(long bookId, long authorId);

    /**
     * @return number of affected rows
     */
    public int unlinkBookAndAuthor(long bookId, long authorId);
}
