package edu.spring.services;

import edu.spring.models.Person;
import edu.spring.repositories.PeopleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PeopleService {
    private final PeopleRepository peopleRepository;

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }
}
