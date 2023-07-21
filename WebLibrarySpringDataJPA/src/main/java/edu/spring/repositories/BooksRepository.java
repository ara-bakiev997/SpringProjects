package edu.spring.repositories;


import edu.spring.models.Book;
import edu.spring.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
    List<Book> findAllByOrderById();

    List<Book> findAllByOwnerId(int id);

    List<Book> findByNameStartingWith(String startingWith);
}
