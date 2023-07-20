package edu.spring.repositories;

import edu.spring.models.Book;
import edu.spring.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
    List<Person> findAllByOrderById();
    Optional<Person> findByFullName(String fullName);
}
