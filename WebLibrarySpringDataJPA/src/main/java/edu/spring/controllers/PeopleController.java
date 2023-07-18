package edu.spring.controllers;


import edu.spring.services.PeopleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/people")
@RequiredArgsConstructor
public class PeopleController {

    private final PeopleService peopleService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("people", peopleService.findAll());
        return "/people/index";
    }
}
