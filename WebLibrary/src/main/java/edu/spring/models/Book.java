package edu.spring.models;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

  private int id;
  @NotEmpty(message = "Название книги не может быть пустым")
  private String name;
  @NotEmpty(message = "Имя автора не может быть пустым")
  @Pattern(regexp = "[А-я][а-я]+ [А-я][а-я]+",
      message = "Имя автора должно соответстовать шаблону: Имя Фамилия")
  private String author;
  @Min(value = 0, message = "Год создания книги не может быть меньше 0")
  private int year;
  private Integer personId;
}
