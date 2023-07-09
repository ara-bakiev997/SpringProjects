package edu.spring.utils;


import edu.spring.dao.PersonDAO;
import edu.spring.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

  private final PersonDAO personDAO;

  @Autowired
  public PersonValidator(PersonDAO personDAO) {
    this.personDAO = personDAO;
  }

  @Override
  public boolean supports(Class<?> clazz) {
    return Person.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    if (target instanceof Person) {
      Person person = (Person) target;
      if (personDAO.getPersonByFullName(((Person) target).getFullName()).isPresent()) {
        errors.rejectValue("fullName", null, "ФИО должно быть уникальным");
      }
    }
  }
}
