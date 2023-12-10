package edu.jwt.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name = "People")
@NoArgsConstructor
@Data
public class Person {

    @Id
    @Column(name = "person_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int personId;

    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min = 2, max = 100, message = "Имя должно быть от 2 до 100 символов длиной")
    @Column(name = "username")
    @NonNull
    private String username;

    @Min(value = 1900, message = "Год рождения должен быть больше, чем 1900")
    @Column(name = "year_of_birth")
    @NonNull
    private int yearOfBirth;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;
}
