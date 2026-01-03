package library.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import library.exception.InvalidCpfException;
import library.valueObject.Cpf;

public class Author {

    public interface PublicView {
    }

    @Null private Long id;
    @NotBlank private String name;
    @NotNull private Cpf cpf;
    private LocalDate dateOfBirth;

    public Author(Long id, String name, String cpf, LocalDate dateOfBirth)
            throws InvalidCpfException {
        this.id = id;
        this.name = name;
        this.cpf = new Cpf(cpf);
        this.dateOfBirth = dateOfBirth;
    }

    @JsonView(PublicView.class)
    public Long getId() {
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

    public String getRawCpf() {
        return cpf.getRawCpf();
    }

    @JsonView(PublicView.class)
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

}
