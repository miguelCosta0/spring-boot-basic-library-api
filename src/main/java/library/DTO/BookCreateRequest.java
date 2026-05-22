package library.DTO;

import jakarta.validation.constraints.NotBlank;

public record BookCreateRequest(
    @NotBlank String title,
    String description) {
}
