package edu.spring.services;

import edu.spring.models.Book;
import edu.spring.models.Person;
import edu.spring.repositories.BooksRepository;
import edu.spring.repositories.PeopleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BooksService {
    private final BooksRepository booksRepository;
    private final PeopleRepository peopleRepository;

    public List<Book> findAll() {
        return booksRepository.findAll();
    }

    @Transactional
    public void save(Book book) {
        booksRepository.save(book);
    }

    public Book findById(int id) {
        return booksRepository.findById(id).orElse(null);
    }

    @Transactional
    public void update(int id, Book book) {
        Book bookToBeUpdated = booksRepository.findById(id).get();

        book.setId(id);
        book.setOwner(bookToBeUpdated.getOwner());
        booksRepository.save(book);
    }

    @Transactional
    public void deleteById(int id) {
        booksRepository.deleteById(id);
    }

    public List<Person> findAllPerson() {
        return peopleRepository.findAll();
    }

    public List<Book> findAllByOrderById() {
        return booksRepository.findAllByOrderById();
    }

    public List<Book> findAllByOrderByIdAndPaging(Integer page, Integer booksPerPage) {
        return booksRepository.findAll(PageRequest.of(page, booksPerPage, Sort.by("id"))).getContent();
    }

    public List<Book> findAllByPagingAndSortingByYear(Integer page, Integer booksPerPage) {
        return booksRepository.findAll(PageRequest.of(page, booksPerPage, Sort.by("year"))).getContent();
    }

    public List<Book> findAllBySortingByYear() {
        return booksRepository.findAll(Sort.by("year"));
    }

    public List<Book> findByNameStartingWith(String startingWith) {
        return booksRepository.findByNameStartingWith(startingWith);
    }

    @Transactional
    public void linkBookWithPerson(int bookId, Person person) {
        booksRepository.findById(bookId).ifPresent(book -> {
            book.setOwner(person);
            book.setDateOfTaking(new Date());
        });
    }

    @Transactional
    public void unlinkBookWithPerson(int bookId) {
        // Можно выставить новые значения так как, объект находится в persistent context
        booksRepository.findById(bookId).ifPresent(book -> {
            book.setOwner(null);
            book.setDateOfTaking(null);
            book.setOverdue(false);
        });
    }

}
