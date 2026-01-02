package library.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import library.exception.InternalServerException;
import library.exception.NotFoundException;
import library.model.Author;
import library.repository.AuthorRepository;
import library.service.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> getAllAuthors() {
        List<Author> authors = authorRepository.getAllAuthors();

        if (authors.size() == 0)
            throw new NotFoundException("There are no authors registered");

        return authors;
    }

    @Override
    public Author getAuthor(long id) {
        Optional<Author> author = authorRepository.getAuthor(id);
        return author
                .orElseThrow(() -> new NotFoundException("There is no author with such id"));
    }

    @Override
    public void createAuthor(Author newAuthor) {
        int res = authorRepository.createAuthor(newAuthor);

        if (res == 0)
            throw new InternalServerException("Author could not be created");

    }

    @Override
    public void updateAuthor(long id, Author author) {
        getAuthor(id);
        int res = authorRepository.updateAuthor(id, author);
        if (res == 0)
            throw new InternalServerException("Author could not be updated");
    }

    @Override
    public void deleteAuthor(long id) {
        getAuthor(id);
        int res = authorRepository.deleteAuthor(id);
        if (res == 0)
            throw new InternalServerException("Author could not be deleted");
    }

}
