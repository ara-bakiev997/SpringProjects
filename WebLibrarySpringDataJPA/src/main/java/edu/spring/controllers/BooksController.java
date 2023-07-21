package edu.spring.controllers;

import edu.spring.models.Book;
import edu.spring.models.Person;
import edu.spring.services.BooksService;
import edu.spring.utils.BookValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class BooksController {
    private final BooksService booksService;
    private final BookValidator bookValidator;

    @GetMapping
    public String index(Model model, @RequestParam(value = "page", required = false) Integer page,
                        @RequestParam(value = "books_per_page", required = false) Integer booksPerPage,
                        @RequestParam(value = "sort_by_year", required = false) Boolean sortByYear) {

        if (page != null && booksPerPage != null && sortByYear != null) {
            model.addAttribute("books", booksService.findAllByPagingAndSortingByYear(page, booksPerPage));
        } else if (page != null && booksPerPage != null) {
            model.addAttribute("books", booksService.findAllByOrderByIdAndPaging(page, booksPerPage));
        } else if (sortByYear != null) {
            model.addAttribute("books", booksService.findAllBySortingByYear());
        } else {
            model.addAttribute("books", booksService.findAllByOrderById());
        }
        return "/books/index";
    }

    @GetMapping("/new")
    public String create(@ModelAttribute("book") Book book) {
        return "/books/new";
    }

    @PostMapping
    public String save(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        bookValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/books/new";
        }
        booksService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
        Book book = booksService.findById(id);
        model.addAttribute("book", book);
        if (book.getOwner() != null) {
            model.addAttribute("owner", book.getOwner());
        } else {
            model.addAttribute("people", booksService.findAllPerson());
        }
        return "/books/show";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", booksService.findById(id));
        return "/books/edit";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult) {
        bookValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/books/edit";
        }
        booksService.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        booksService.deleteById(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/link")
    public String link(@PathVariable("id") int id, @ModelAttribute("person") Person person) {
        booksService.linkBookWithPerson(id, person);
        return "redirect:/books/{id}";
    }

    @PatchMapping("/{id}/unlink")
    public String unlink(@PathVariable("id") int id) {
        booksService.unlinkBookWithPerson(id);
        return "redirect:/books/{id}";
    }

    @GetMapping("/search")
    public String searchPage() {
        return "/books/search";
    }

    @PostMapping("/search")
    public String makeSearch(@RequestParam(value = "search_template") String searchTemplate, Model model) {
        List<Book> books = booksService.findByNameStartingWith(searchTemplate);
        model.addAttribute("books", books);
        return "/books/search";
    }


}
