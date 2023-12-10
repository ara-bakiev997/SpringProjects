package edu.jwt.repositories;

import edu.jwt.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByUsername(final String username);
}
