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

    System.out.println(books);
    return books;


  }


  public Object getBookById(int id) {
    return jdbcTemplate.queryForObject("select * from book where id = ?;",
        new BeanPropertyRowMapper<>(Book.class),
        id
    );
  }
}
