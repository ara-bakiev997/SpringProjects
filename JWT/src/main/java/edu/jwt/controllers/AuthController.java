package edu.jwt.controllers;

import edu.jwt.dtos.AuthenticationDTO;
import edu.jwt.dtos.PersonDTO;
import edu.jwt.models.Person;
import edu.jwt.security.JWTUtil;
import edu.jwt.services.RegistrationService;
import edu.jwt.util.PersonValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final PersonValidator personValidator;
    private final RegistrationService registrationService;
    private final JWTUtil jwtUtil;
    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;

//    @GetMapping("/login")
//    public String loginPage() {
//        return "/auth/login.html";
//    }
//
//    @GetMapping("/registration")
//    public String registrationPage(@ModelAttribute("person") Person person) {
//        return "/auth/registration";
//    }

    @PostMapping("/registration")
    public Map<String, String> performRegistration(
            @RequestBody @Valid final PersonDTO personDTO,
            final BindingResult bindingResult
    ) {
        final Person person = convertToPerson(personDTO);
        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors()) {
            return Map.of("message", "Ошибка!");
        }
        registrationService.register(person);

        final String token = jwtUtil.generateToken(person.getUsername());
        return Collections.singletonMap("jwt-token", token);
    }

    @PostMapping("/login")
    public Map<String, String> performLogin(@RequestBody final AuthenticationDTO authenticationDTO) {
        final UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        authenticationDTO.getUsername(),
                        authenticationDTO.getPassword()
                );
        try {
            authenticationManager.authenticate(authenticationToken);
        } catch (final BadCredentialsException e) {

            return Map.of("message", "Incorrect credentials");
        }
        final String generateToken = jwtUtil.generateToken(authenticationDTO.getUsername());

        return Map.of("jwt-token", generateToken);
    }

    private Person convertToPerson(final PersonDTO personDTO) {
        return modelMapper.map(personDTO, Person.class);
    }

}
