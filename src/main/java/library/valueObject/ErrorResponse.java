package library.valueObject;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonView;

public class ErrorResponse {

    public interface PublicView {
    }

    private final LocalDateTime timestamp;
    private final List<String> errors;
    private final HttpStatus status;

    public ErrorResponse(HttpStatus status, List<String> errors) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.errors = errors;
    }

    public ErrorResponse(HttpStatus status, String... errors) {
        this(status, List.of(errors));
    }

    @JsonView(PublicView.class)
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @JsonView(PublicView.class)
    public List<String> getErrors() {
        return errors;
    }

    @JsonView(PublicView.class)
    public int getStatus() {
        return status.value();
    }

}
