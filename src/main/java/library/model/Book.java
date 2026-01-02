package library.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

public class Book {

    public interface PublicView {
    }

    @Null private Long id;
    @NotNull private String title;
    private String description;
    private List<Long> authorIds = new ArrayList<>();

    public Book() {}

    public Book(Long id, String title, String description, List<Long> authorIds) {
        if (title == null) {
            throw new NullPointerException("Title can't be null."); // TODO fazer Exception direito
        }
        this.id = id;
        this.title = title;
        this.description = description;
        if (authorIds != null) {
            authorIds.addAll(authorIds);
        }
        // this.authorIds = new ArrayList<>(authorIds == null ? authorIds);
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
        return authorIds;
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
        this.authorIds = authorIds;
    }

}
