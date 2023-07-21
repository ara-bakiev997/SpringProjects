package edu.springboot.repositories;

import edu.springboot.models.Book;
import edu.springboot.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
    List<Person> findAllByOrderById();
    Optional<Person> findByFullName(String fullName);
}
