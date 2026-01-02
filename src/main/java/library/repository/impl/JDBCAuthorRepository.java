package library.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import library.model.Author;
import library.repository.AuthorRepository;

@Repository
public class JDBCAuthorRepository implements AuthorRepository {

    private final RowMapper<Author> authorRowMapper = new RowMapper<>() {
        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            Long id = rs.getLong(1);
            String name = rs.getString(2);
            String cpf = rs.getString(3);
            LocalDate dateOfBirth = rs.getDate(4).toLocalDate();

            return new Author(id, name, cpf, dateOfBirth);
        }
    };

    private final JdbcTemplate jdbcTemplate;

    public JDBCAuthorRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Author> getAuthor(long id) {
        var sql = "SELECT id, name, cpf, date_of_birth FROM Author WHERE id = ?";
        Object[] args = {id};
        int[] argTypes = {Types.INTEGER};

        List<Author> result = jdbcTemplate.query(sql, args, argTypes, authorRowMapper);

        return result.stream().findFirst();
    }

    @Override
    public List<Author> getAllAuthors() {
        var sql = "SELECT id, name, cpf, date_of_birth FROM Author";

        List<Author> result = jdbcTemplate.query(sql, authorRowMapper);

        return result;
    }

    @Override
    public int createAuthor(Author author) {
        var sql = """
                INSERT INTO Author (name, cpf, date_of_birth) VALUES (?, ?, ?)
                """;
        Object[] args = {author.getName(), author.getCpf(), author.getDateOfBirth()};
        int[] argTypes = {Types.VARCHAR, Types.VARCHAR, Types.DATE};

        return jdbcTemplate.update(sql, args, argTypes);
    }

    @Override
    public int deleteAuthor(long id) {
        var sql = """
                DELETE FROM Author WHERE id = ?
                """;
        Object[] args = {id};
        int[] argTypes = {Types.INTEGER};

        return jdbcTemplate.update(sql, args, argTypes);
    }

    @Override
    public int updateAuthor(long id, Author author) {
        var sql = """
                UPDATE Author SET name = ?, cpf = ?, date_of_birth = ? WHERE id = ?
                """;
        Object[] args =
                {author.getName(), author.getCpf(), author.getDateOfBirth(), author.getId()};
        int[] argTypes = {Types.VARCHAR, Types.VARCHAR, Types.DATE, Types.INTEGER};

        return jdbcTemplate.update(sql, args, argTypes);
    }

}
