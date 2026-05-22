package library.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import library.DTO.AuthorCreateRequest;
import library.DTO.AuthorUpdateRequest;
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
    public void createAuthor(AuthorCreateRequest authorDto) {
        var author = new Author(
            authorDto.name(),
            authorDto.cpf(),
            authorDto.dateOfBirth());

        author = authorRepository.saveAndFlush(author);

        if (author == null)
            throw new InternalServerException("Author could not be created");
    }

    @Override
    public void updateAuthor(long id, AuthorUpdateRequest authorDto) {
        var author = getAuthor(id);

        if (authorDto.name() != null)
            author.setName(authorDto.name());
        if (authorDto.cpf() != null)
            author.setCpf(authorDto.cpf());
        if (authorDto.dateOfBirth() != null)
            author.setDateOfBirth(authorDto.dateOfBirth());

        author = authorRepository.saveAndFlush(author);
        if (author == null)
            throw new InternalServerException("Author could not be updated");
    }

    @Override
    public void deleteAuthor(long id) {
        getAuthor(id);
        authorRepository.deleteById(id);
    }

}
