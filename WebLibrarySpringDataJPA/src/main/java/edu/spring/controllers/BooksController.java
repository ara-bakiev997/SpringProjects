package edu.spring.controllers;

import edu.spring.models.Book;
import edu.spring.services.BooksService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class BooksController {
    private final BooksService booksService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("books", booksService.findAll());
        return "/books/index";
    }

    @GetMapping("/new")
    public String create(@ModelAttribute("book") Book book) {
        return "/books/new";
    }

    @PostMapping
    public String save(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/books/new";
        }
        booksService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", booksService.findById(id));
        return "books/show";
    }





}
