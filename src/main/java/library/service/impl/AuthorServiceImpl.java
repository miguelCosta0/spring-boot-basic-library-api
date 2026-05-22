package library.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import library.DTO.AuthorRequestDTO;
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
        List<Author> authors = authorRepository.findAll();

        if (authors.size() == 0)
            throw new NotFoundException("There are no authors registered");

        return authors;
    }

    @Override
    public Author getAuthor(long id) {
        var author = authorRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException("There is no author with such id"));
        return author;
    }

    @Override
    public void createAuthor(AuthorRequestDTO authorReq) {
        var author = new Author(
            authorReq.name(),
            authorReq.cpf(),
            authorReq.dateOfBirth());

        author = authorRepository.saveAndFlush(author);

        if (author == null)
            throw new InternalServerException("Author could not be created");
    }

    @Override
    public void updateAuthor(long id, AuthorRequestDTO authorReq) {
        var author = getAuthor(id);

        author.setName(authorReq.name());
        author.setCpf(authorReq.cpf());
        author.setDateOfBirth(authorReq.dateOfBirth());

        author = authorRepository.saveAndFlush(author);
        if (author == null)
            throw new InternalServerException("Author could not be updated");
    }

    @Override
    public void deleteAuthor(long id) {
        authorRepository.deleteById(id);
    }

}
