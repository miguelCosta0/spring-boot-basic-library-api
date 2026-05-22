package library.service;

import java.util.List;
import library.DTO.AuthorCreateRequest;
import library.DTO.AuthorUpdateRequest;
import library.model.Author;

public interface AuthorService {

    public List<Author> getAllAuthors();

    public Author getAuthor(long id);

    public void createAuthor(AuthorCreateRequest authorDto);

    public void updateAuthor(long id, AuthorUpdateRequest authorDto);

    public void deleteAuthor(long id);

}
