package edu.springboot.utils;

import edu.springboot.models.Person;
import edu.springboot.repositories.PeopleRepository;
import edu.springboot.services.PeopleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@RequiredArgsConstructor
public class PersonValidator implements Validator{
    private final PeopleService peopleService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;
        if (peopleService.findByFullName(person.getFullName()).isPresent()) {
            errors.rejectValue("fullName", null, "ФИО должно быть уникальным");
        }

        if (person.getYearOfBirth() <= 0) {
            errors.rejectValue("yearOfBirth", null, "Год рождения должен быть больше 0");
        }

    }
}
