package edu.spring.controllers;


import edu.spring.dao.BookDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/books")
public class BookController {

  private final BookDAO bookDAO;

  @Autowired
  public BookController(BookDAO bookDAO) {
    this.bookDAO = bookDAO;
  }

  @GetMapping()
  public String index(Model model) {
    model.addAttribute("books", bookDAO.getBooks());
    return "/books/index";
  }

  @GetMapping("/{id}")
  public String show(@PathVariable("id") int id, Model model) {
    model.addAttribute("book", bookDAO.getBookById(id));
    return "/books/show";
  }




}
