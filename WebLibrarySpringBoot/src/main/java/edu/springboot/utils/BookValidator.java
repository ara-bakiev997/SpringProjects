package edu.springboot.utils;

import edu.springboot.models.Book;
import edu.springboot.services.BooksService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class BookValidator implements Validator {
    private final BooksService booksService;
    @Override
    public boolean supports(Class<?> aClass) {
        return Book.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Book book = (Book) o;
        if (book.getYear() <= 0) {
            errors.rejectValue("year", null, "Год написания книги должно быть больше 0");
        }
    }
}
