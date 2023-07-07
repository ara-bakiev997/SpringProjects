package edu.spring.controllers;


import edu.spring.dao.BookDAO;
import edu.spring.dao.PersonDAO;
import edu.spring.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/books")
public class BookController {

  private final BookDAO bookDAO;

  private final PersonDAO personDAO;

  @Autowired
  public BookController(BookDAO bookDAO, PersonDAO personDAO) {
    this.bookDAO = bookDAO;
    this.personDAO = personDAO;
  }

  @GetMapping()
  public String index(Model model) {
    model.addAttribute("books", bookDAO.getBooks());
    return "/books/index";
  }

  @GetMapping("/{id}")
  public String show(@PathVariable("id") int id, Model model) {
    Book book = bookDAO.getBookById(id);

    System.out.println("Person id = " + book.getPersonId());

    model.addAttribute("book", book);
    if (book.getPersonId() == null) {
      model.addAttribute("people", personDAO.getPeople());
    } else {
      model.addAttribute("person", personDAO.getPersonById(book.getPersonId()));
    }
    return "/books/show";
  }

  @GetMapping("/new")
  public String newBook(@ModelAttribute("book") Book book) {
    return "/books/new";
  }

  @PostMapping()
  public String save(@ModelAttribute("book") Book book) {
    bookDAO.save(book);
    return "redirect:/books";
  }

  @GetMapping("/{id}/edit")
  public String editBook(@PathVariable("id") int id, Model model) {
    model.addAttribute("book", bookDAO.getBookById(id));
    return "/books/edit";
  }

  @PatchMapping("/{id}")
  public String update(@PathVariable("id") int id, @ModelAttribute("book") Book book) {
    bookDAO.update(id, book);
    return "redirect:/books";
  }

  @DeleteMapping("/{id}")
  public String delete(@PathVariable("id") int id) {
    bookDAO.delete(id);
    return "redirect:/books";
  }

  @PatchMapping("/{id}/release")
  public String releaseBook(@PathVariable("id") int id) {
    bookDAO.unlinkBookById(id);
    return "redirect:/books";
  }

}
