package edu.jwt.services;

import edu.jwt.models.Person;
import edu.jwt.repositories.PeopleRepository;
import edu.jwt.security.PersonDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PersonDetailsService implements UserDetailsService {
    private final PeopleRepository peopleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> person = peopleRepository.findByUsername(username);

        if (person.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        return new PersonDetails(person.get());
    }
}
