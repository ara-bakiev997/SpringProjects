package edu.spring.controllers;

import edu.spring.dao.PersonDAO;
import edu.spring.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/people")
public class PersonController {

  private final PersonDAO personDAO;

  @Autowired
  public PersonController(PersonDAO personDAO) {
    this.personDAO = personDAO;
  }

  @GetMapping()
  public String index(Model model) {
    model.addAttribute("people", personDAO.getPeople());
    return "people/index";
  }

  @GetMapping("/{id}")
  public String show(@PathVariable("id") int id, Model model) {
    model.addAttribute("person", personDAO.getPersonById(id));
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


}
