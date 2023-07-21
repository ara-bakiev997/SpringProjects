package edu.springboot.repositories;


import edu.springboot.models.Book;
import edu.springboot.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
    List<Book> findAllByOrderById();

    List<Book> findAllByOwnerId(int id);

    List<Book> findByNameStartingWith(String startingWith);
}
