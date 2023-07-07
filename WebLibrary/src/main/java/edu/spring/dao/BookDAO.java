package edu.spring.dao;

import edu.spring.models.Book;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class BookDAO {

  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public BookDAO(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<Book> getBooks() {
    List<Book> books = jdbcTemplate.query("select * from book;",
        new BeanPropertyRowMapper<>(Book.class));
    return books;
  }


  public Object getBookById(int id) {
    return jdbcTemplate.queryForObject("select * from book where id = ?;",
        new BeanPropertyRowMapper<>(Book.class),
        id
    );
  }

  public void save(Book book) {
    jdbcTemplate.update("insert into Book (name, author, year, person_id) values (?, ?, ?, ?);",
        book.getName(), book.getAuthor(), book.getYear(), book.getPersonId());
  }

  public void update(int id, Book book) {
    jdbcTemplate.update(
        "update book set name = ?, author = ?, year = ?, person_id = ? where id = ?",
        book.getName(), book.getAuthor(), book.getYear(), book.getPersonId(), id
    );
  }

  public void delete(int id) {
    jdbcTemplate.update("delete from book where id = ?;", id);
  }
}
