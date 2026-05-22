package library;

import java.time.LocalDate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.UrlHandlerFilter;
import library.model.Author;
import library.model.Book;
import library.repository.AuthorRepository;
import library.repository.BookRepository;

@SpringBootApplication
public class LibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryApplication.class, args);
    }

    @Bean
    public UrlHandlerFilter handleTrailingSlashUrlPath() {
        return UrlHandlerFilter
            .trailingSlashHandler("/api/**")
            .redirect(HttpStatus.PERMANENT_REDIRECT)
            .build();
    }

    @Bean
    CommandLineRunner commandLineRunner(
        AuthorRepository authorRepository,
        BookRepository bookRepository) {
        return args -> {
            Author[] authors = {
                new Author("Machado de Assis", "197.138.604-97", LocalDate.of(1839, 6, 21)),
                new Author("Clarice Lispector", "141.110.075-11", LocalDate.of(1920, 12, 10)),
                new Author("Jorge Amado", "375.752.110-20", LocalDate.of(1912, 8, 10)),
                new Author("Carlos Drummond", "987.889.171-26", LocalDate.of(1902, 10, 31)),
            };

            Book[] books = {
                new Book("Dom Casmurro",
                    "A classic psychological novel exploring jealousy and ambiguity."),
                new Book("Ou Isto ou Aquilo",
                    "A delightful, rhythmic masterpiece of children's poetry."),
                new Book("Grande Sertão: Veredas",
                    "An epic philosophical journey through the Brazilian backcountry and the human soul."),
                new Book("O Auto da Compadecida",
                    "A brilliant, comedic folk-play blending satire, religion, and northern culture."),
                new Book("Vidas Secas",
                    "A gritty, minimalist masterpiece following a nomad family fleeing the arid drought."),
                new Book("As Meninas",
                    "A poignant story following three young women navigating life during the military dictatorship.")
            };

            for (Author a : authors) {
                a = authorRepository.saveAndFlush(a);
            }
            for (Book b : books) {
                b = bookRepository.saveAndFlush(b);
            }

            authors[0].addBook(books[0]);
            authors[0].addBook(books[1]);
            authors[1].addBook(books[2]);
            authors[2].addBook(books[3]);
            authors[2].addBook(books[3]);

            for (Author a : authors) {
                authorRepository.saveAndFlush(a);
            }
            for (Book b : books) {
                bookRepository.saveAndFlush(b);
            }
        };
    }
}
