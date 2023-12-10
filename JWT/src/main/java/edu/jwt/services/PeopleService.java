package edu.jwt.services;

import edu.jwt.models.Person;
import edu.jwt.repositories.PeopleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PeopleService {
    private final PeopleRepository peopleRepository;

    public Optional<Person> findByUsername(String username) {
        return peopleRepository.findByUsername(username);
    }

}
