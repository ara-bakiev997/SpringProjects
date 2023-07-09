package edu.spring.dao;

import edu.spring.models.Person;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class PersonDAO {

  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public PersonDAO(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<Person> getPeople() {
    return jdbcTemplate.query("select * from person order by id;", new BeanPropertyRowMapper<>(Person.class));
  }

  public Person getPersonById(int id) {
    return jdbcTemplate.queryForObject("select * from person where id = ?;",
        new BeanPropertyRowMapper<>(
            Person.class), id);
  }

  public void save(Person person) {
    jdbcTemplate.update("insert into Person (full_name, year) values (?, ?);",
        person.getFullName(), person.getYear());
  }

  public void update(int id, Person person) {
    jdbcTemplate.update("update Person set full_name = ?, year = ? where id = ?;",
        person.getFullName(), person.getYear(), id
    );
  }

  public void delete(int id) {
    jdbcTemplate.update("delete from Person where id = ?;", id);
  }
}
