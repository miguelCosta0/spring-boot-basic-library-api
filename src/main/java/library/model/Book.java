package library.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

public class Book {

    public interface PublicView {
    }

    @Null private Long id;
    @NotBlank private String title;
    private String description;
    private List<@NotNull Long> authorIds = new ArrayList<>();

    public Book(Long id, String title, String description, List<Long> authorIds) {
        this.id = id;
        this.title = title;
        this.description = description;
        if (authorIds != null) {
            this.authorIds.addAll(authorIds);
        }
    }

    @JsonView(PublicView.class)
    public Long getId() {
        return id;
    }

    @JsonView(PublicView.class)
    public String getTitle() {
        return title;
    }

    @JsonView(PublicView.class)
    public String getDescription() {
        return description;
    }

    @JsonView(PublicView.class)
    public List<Long> getAuthorIds() {
        return Collections.unmodifiableList(authorIds);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAuthorIds(List<Long> authorIds) {
        this.authorIds.clear();
        this.authorIds.addAll(authorIds);
    }

}
