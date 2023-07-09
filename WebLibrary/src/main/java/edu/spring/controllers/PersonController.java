package edu.spring.controllers;

import edu.spring.dao.BookDAO;
import edu.spring.dao.PersonDAO;
import edu.spring.models.Person;
import edu.spring.utils.PersonValidator;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/people")
public class PersonController {

  private final PersonDAO personDAO;
  private final BookDAO bookDAO;

  private final PersonValidator personValidator;

  @Autowired
  public PersonController(PersonDAO personDAO, BookDAO bookDAO, PersonValidator personValidator) {
    this.personDAO = personDAO;
    this.bookDAO = bookDAO;
    this.personValidator = personValidator;
  }

  @GetMapping()
  public String index(Model model) {
    model.addAttribute("people", personDAO.getPeople());
    return "people/index";
  }

  @GetMapping("/{id}")
  public String show(@PathVariable("id") int id, Model model) {
    model.addAttribute("person", personDAO.getPersonById(id));
    model.addAttribute("books", bookDAO.getPersonBooksById(id));
    return "/people/show";
  }

  @GetMapping("/new")
  public String newPerson(@ModelAttribute("person") Person person) {
    return "/people/new";
  }

  @PostMapping()
  public String save(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
    personValidator.validate(person, bindingResult);

    if (bindingResult.hasErrors()) {
      return "/people/new";
    }

    personDAO.save(person);
    return "redirect:/people";
  }

  @GetMapping("/{id}/edit")
  public String editPerson(@PathVariable("id") int id, Model model) {
    model.addAttribute("person", personDAO.getPersonById(id));
    return "/people/edit";
  }

  @PutMapping("/{id}") // or PatchMapping
  public String update(@PathVariable("id") int id, @ModelAttribute("person") @Valid Person person,
      BindingResult bindingResult) {
    personValidator.validate(person, bindingResult);

    if (bindingResult.hasErrors()) {
      return "/people/edit";
    }

    personDAO.update(id, person);
    return "redirect:/people";
  }

  @DeleteMapping("/{id}")
  public String delete(@PathVariable("id") int id) {
    personDAO.delete(id);
    return "redirect:/people";
  }

}
