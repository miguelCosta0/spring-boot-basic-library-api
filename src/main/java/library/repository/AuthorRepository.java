package library.repository;

import java.util.List;
import java.util.Optional;
import library.model.Author;

public interface AuthorRepository {

    /**
     * @return the Author (if exists)
     */
    public Optional<Author> getAuthor(long id);

    /**
     * @return a List of all registered authors
     */
    public List<Author> getAllAuthors();

    /**
     * @return number of affected rows
     */
    public int createAuthor(Author Author);

    /**
     * @return number of affected rows
     */
    public int updateAuthor(long id, Author newAuthor);

    /**
     * @return number of affected rows
     */
    public int deleteAuthor(long id);

}
