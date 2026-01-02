package library.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import library.model.Book;
import library.repository.BookRepository;

@Repository
public class JDBCBookRepository implements BookRepository {

    private final RowMapper<Book> bookRowMapper = new RowMapper<>() {
        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            Long id = rs.getLong(1);
            String title = rs.getString(2);
            String description = rs.getString(3);

            List<Long> authorIds = jdbcTemplate.query(
                    "SELECT Author_id, Book_id FROM AuthorBook WHERE Book_id = ?",
                    new Object[] {id},
                    new int[] {Types.INTEGER},
                    (resSet, rn) -> resSet.getLong(1));

            return new Book(id, title, description, authorIds);
        }
    };

    private final JdbcTemplate jdbcTemplate;

    public JDBCBookRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Book> getBook(long id) {
        var sql = "SELECT id, title, description FROM Book WHERE id = ?";
        Object[] args = {id};
        int[] argTypes = {Types.INTEGER};

        List<Book> result = jdbcTemplate.query(sql, args, argTypes, bookRowMapper);

        return result.stream().findFirst();
    }

    @Override
    public List<Book> getAllBooks() {
        var sql = "SELECT id, title, description FROM Book";

        List<Book> result = jdbcTemplate.query(sql, bookRowMapper);

        return result;
    }

    @Override
    public int createBook(Book book) {
        var sql = """
                INSERT INTO Book (title, description) VALUES (?, ?)
                """;
        Object[] args = {book.getTitle(), book.getDescription()};
        int[] argTypes = {Types.VARCHAR, Types.VARCHAR};

        return jdbcTemplate.update(sql, args, argTypes);
    }

    @Override
    public int deleteBook(long id) {
        var sql = """
                DELETE FROM Book WHERE id = ?
                """;
        Object[] args = {id};
        int[] argTypes = {Types.INTEGER};

        return jdbcTemplate.update(sql, args, argTypes);
    }

    @Override
    public int updateBook(long id, Book newBook) {
        var sql = """
                UPDATE Book SET title = ?, description = ? WHERE id = ?
                """;
        Object[] args = {newBook.getTitle(), newBook.getDescription(), id};
        int[] argTypes = {Types.VARCHAR, Types.VARCHAR, Types.INTEGER};

        return jdbcTemplate.update(sql, args, argTypes);
    }

    @Override
    public int linkBookAndAuthor(long bookId, long authorId) {
        var sql = """
                INSERT INTO AuthorBook (Book_id, Author_id) VALUES (?, ?)
                """;
        Object[] args = {bookId, authorId};
        int[] argTypes = {Types.INTEGER, Types.INTEGER};

        return jdbcTemplate.update(sql, args, argTypes);
    }

    @Override
    public int unlinkBookAndAuthor(long bookId, long authorId) {
        var sql = """
                DELETE FROM AuthorBook WHERE Book_id = ? AND Author_id = ?
                """;
        Object[] args = {bookId, authorId};
        int[] argTypes = {Types.INTEGER, Types.INTEGER};

        return jdbcTemplate.update(sql, args, argTypes);
    }
}
