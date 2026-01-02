package library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.UrlHandlerFilter;

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
}
