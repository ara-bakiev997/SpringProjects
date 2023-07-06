package edu.spring.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class TableInitializer {

  @Autowired
  public TableInitializer(JdbcTemplate jdbcTemplate, String pathToSchema) {
    Path path = Paths.get(pathToSchema);

    try {
      String query = Files.lines(path).collect(Collectors.joining("\n"));
      System.out.println(query);
      jdbcTemplate.update(query);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
