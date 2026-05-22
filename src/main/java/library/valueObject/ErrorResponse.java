package library.valueObject;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.http.HttpStatus;

public class ErrorResponse {

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

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public List<String> getErrors() {
        return errors;
    }

    public int getStatus() {
        return status.value();
    }

}
