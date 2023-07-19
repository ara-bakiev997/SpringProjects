package edu.spring.services;

import edu.spring.models.Book;
import edu.spring.repositories.BooksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BooksService {
    private final BooksRepository booksRepository;

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
    public void update(Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void deleteById(int id) {
        booksRepository.deleteById(id);
    }
}
