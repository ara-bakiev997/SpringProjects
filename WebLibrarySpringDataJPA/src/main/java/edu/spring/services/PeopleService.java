package edu.spring.services;

import edu.spring.models.Book;
import edu.spring.models.Person;
import edu.spring.repositories.BooksRepository;
import edu.spring.repositories.PeopleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PeopleService {
    private final PeopleRepository peopleRepository;
    private final BooksRepository booksRepository;

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    @Transactional
    public void save(Person person) {
        peopleRepository.save(person);
    }

    public Person findById(int id) {
        return peopleRepository.findById(id).orElse(null);
    }

    @Transactional
    public void update(int id, Person person) {
        person.setId(id);
        peopleRepository.save(person);
    }

    @Transactional
    public void deleteById(int id) {
        peopleRepository.deleteById(id);
    }

    public Optional<Person> findByFullName(String fullName) {
        return peopleRepository.findByFullName(fullName);
    }

    public List<Book> findBooksByPersonId(int id) {
        List<Book> books = booksRepository.findAllByOwnerId(id);
        for (Book book : books) {
            if (book.getDateOfTaking() != null) {
                int days = Period.between(book.getDateOfTaking().toInstant()
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate(),
                        LocalDate.now(ZoneId.systemDefault())).getDays();
                if (days > 10) {
                    book.setOverdue(true);
                }
            }
        }
        return books;
    }

    public List<Person> findAllByOrderById() {
        return peopleRepository.findAllByOrderById();
    }
}
