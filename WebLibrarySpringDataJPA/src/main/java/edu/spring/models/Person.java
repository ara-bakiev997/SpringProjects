package edu.spring.models;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@Table(name = "Person")
@Data
@NoArgsConstructor
public class Person {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "full_name")
    @NotEmpty(message = "ФИО не может быть пустым")
    @Pattern(regexp = "[А-Я][а-я]+ [А-Я][а-я]+ [А-Я][а-я]+",
            message = "ФИО должно соответствовать шаблону: Фамилия Имя Отчество")
    private String fullName;

    @Column(name = "year_of_birth")
    @Min(value = 0, message = "Год рождения не может быть меньше 0")
    private int yearOfBirth;


    @OneToMany(mappedBy = "owner", cascade = CascadeType.PERSIST)
    @ToString.Exclude
    private List<Book> books;

}
