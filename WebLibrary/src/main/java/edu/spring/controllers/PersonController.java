package edu.spring.controllers;

import edu.spring.dao.BookDAO;
import edu.spring.dao.PersonDAO;
import edu.spring.models.Book;
import edu.spring.models.Person;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/people")
public class PersonController {

  private final PersonDAO personDAO;
  private final BookDAO bookDAO;

  @Autowired
  public PersonController(PersonDAO personDAO, BookDAO bookDAO) {
    this.personDAO = personDAO;
    this.bookDAO = bookDAO;
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
  public String save(@ModelAttribute("person") Person person) {
    personDAO.save(person);
    return "redirect:/people";
  }

  @GetMapping("/{id}/edit")
  public String editPerson(@PathVariable("id") int id, Model model) {
    model.addAttribute("person", personDAO.getPersonById(id));
    return "/people/edit";
  }

  @PutMapping("/{id}") // or PatchMapping
  public String update(@PathVariable("id") int id, @ModelAttribute("person") Person person) {
    personDAO.update(id, person);
    return "redirect:/people";
  }

  @DeleteMapping("/{id}")
  public String delete(@PathVariable("id") int id) {
    personDAO.delete(id);
    return "redirect:/people";
  }

}
