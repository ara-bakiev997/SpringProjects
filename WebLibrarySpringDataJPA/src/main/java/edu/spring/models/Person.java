package edu.spring.models;


import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
public class Person {

    private int id;
    @NotEmpty(message = "ФИО не может быть пустым")
    @Pattern(regexp = "[А-Я][а-я]+ [А-Я][а-я]+ [А-Я][а-я]+",
            message = "ФИО должно соответствовать шаблону: Фамилия Имя Отчество")
    private String fullName;
    @Min(value = 0, message = "Год рождения не может быть меньше 0")
    private int yearOfBirth;
}
