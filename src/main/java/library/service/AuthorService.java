package library.service;

import java.util.List;

import library.model.Author;

public interface AuthorService {

    public List<Author> getAllAuthors();

    public Author getAuthor(long id);

    public void createAuthor(Author newAuthor);

    public void updateAuthor(long id, Author Author);

    public void deleteAuthor(long id);

}
