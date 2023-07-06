package edu.spring.controllers;

import edu.spring.dao.PersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/people")
public class PersonController {

  private final PersonDAO personDAO;

  @Autowired
  public PersonController(PersonDAO personDAO) {
    this.personDAO = personDAO;
  }

  @GetMapping
  public String index(Model model) {
    model.addAttribute("people", personDAO.index());
    return "people/index";
  }
}
