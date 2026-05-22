package library.service;

import java.util.List;
import library.DTO.AuthorRequestDTO;
import library.model.Author;

public interface AuthorService {

    public List<Author> getAllAuthors();

    public Author getAuthor(long id);

    public void createAuthor(AuthorRequestDTO newAuthor);

    public void updateAuthor(long id, AuthorRequestDTO Author);

    public void deleteAuthor(long id);

}
