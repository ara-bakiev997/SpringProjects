package edu.jwt.util;

import edu.jwt.models.Person;
import edu.jwt.services.PeopleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PersonValidator implements Validator {
    private final PeopleService peopleService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        Optional<Person> personToBeCheck = peopleService.findByUsername(person.getUsername());
        if (personToBeCheck.isPresent()) {
            errors.rejectValue("username", "", "Человек с таким именем пользователя уже существует");
        }

    }
}
