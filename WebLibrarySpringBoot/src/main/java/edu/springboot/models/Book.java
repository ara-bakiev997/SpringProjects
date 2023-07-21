package edu.springboot.models;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import java.util.Date;

@Entity
@Table(name = "Book")
@Data
@ToString(of = {"id", "name", "author", "year"})
@NoArgsConstructor
public class Book {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "Название книги не может быть пустым")
    private String name;

    @Column(name = "author")
    @NotEmpty(message = "Имя автора не может быть пустым")
    @Pattern(regexp = "[А-я][а-я]+ [А-я][а-я]+",
            message = "Имя автора должно соответстовать шаблону: Имя Фамилия")
    private String author;

    @Column(name = "year")
    @Min(value = 0, message = "Год создания книги не может быть меньше 0")
    private int year;

    @Column(name = "date_of_taking")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfTaking;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person owner;

    @Transient
    private boolean isOverdue;
}

