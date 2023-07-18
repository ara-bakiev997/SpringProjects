package edu.spring.services;

import edu.spring.repositories.BooksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BooksService {
    private final BooksRepository booksRepository;

}
