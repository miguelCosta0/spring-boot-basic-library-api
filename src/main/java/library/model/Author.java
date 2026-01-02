package library.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.Null;
import library.exception.InvalidCpfException;
import library.valueObject.Cpf;

public class Author {

    public interface PublicView {
    }

    @Null private long id;
    @Nonnull private String name;
    @Nonnull private Cpf cpf;
    private LocalDate dateOfBirth;

    public Author(long id, String name, String cpf, LocalDate dateOfBirth)
            throws InvalidCpfException {
        this.id = id;
        this.name = name;
        this.cpf = new Cpf(cpf);
        this.dateOfBirth = dateOfBirth;
    }

    @JsonView(PublicView.class)
    public long getId() {
        return id;
    }

    @JsonView(PublicView.class)
    public String getName() {
        return name;
    }

    @JsonView(PublicView.class)
    public String getCpf() {
        return cpf.toString();
    }

    @JsonView(PublicView.class)
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

}
