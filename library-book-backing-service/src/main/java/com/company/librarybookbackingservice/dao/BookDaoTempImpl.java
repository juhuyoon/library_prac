package com.company.librarybookbackingservice.dao;

import com.company.librarybookbackingservice.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BookDaoTempImpl implements BookDao {

    private JdbcTemplate jdbcTemplate;

    //Prepared Statements
    private static final String CREATE_BOOK_SQL =
            "INSERT INTO book(isbn, title, author) VALUES (?, ?, ?)";

    private static final String GET_BOOK_SQL =
            "SELECT * FROM book WHERE id = ?";

    private static final String GET_ALL_BOOKS_SQL =
            "SELECT * FROM book";

    private static final String GET_BOOK_BY_ISBN_SQL =
            "SELECT * FROM book where ISBN = ?";

    private static final String UPDATE_BOOK_SQL =
            "UPDATE book SET isbn = ?, title = ?, author = ?, WHERE id = ?";

    private static final String DELETE_BOOK_SQL =
            "DELETE FROM book WHERE id = ?";

    @Autowired
    public BookDaoTempImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Book createBook(Book book) {
        jdbcTemplate.update(
                CREATE_BOOK_SQL,
                book.getIsbn(),
                book.getTitle(),
                book.getAuthor()
        );
        int id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        book.setId(id);
        return book;
    }

    @Override
    public Book getBook(int id) {
        try {
            return jdbcTemplate.queryForObject(GET_BOOK_SQL, this::mapRowToBook, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Book> getAllBooks() {
        return jdbcTemplate.query(GET_ALL_BOOKS_SQL, this::mapRowToBook);
    }

    @Override
    public Book getBookByIsbn(String isbn) {
        try {
            return jdbcTemplate.queryForObject(GET_BOOK_BY_ISBN_SQL, this::mapRowToBook, isbn);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void updateBook(Book book) {
        jdbcTemplate.update(UPDATE_BOOK_SQL,
                book.getIsbn(),
                book.getTitle(),
                book.getAuthor(),
                book.getId());
    }

    @Override
    public void deleteBook(int id) {
        jdbcTemplate.update(DELETE_BOOK_SQL, id);
    }

    private Book mapRowToBook(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        book.setId(rs.getInt("id"));
        book.setIsbn(rs.getString("isbn"));
        book.setTitle(rs.getString("title"));
        book.setAuthor(rs.getString("author"));

        return book;
    }
}
