package ru.itmo.lab1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "album")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotBlank
    @Column(name = "name")
    private String name; //Поле не может быть null, Строка не может быть пустой

    @Positive
    @Column(name = "tracks")
    private Long tracks; //Поле может быть null, Значение поля должно быть больше 0

    @Positive
    @Column(name = "length")
    private Long length; //Поле может быть null, Значение поля должно быть больше 0

    @Positive
    @Column(name = "sales", nullable = false)
    private Integer sales; //Значение поля должно быть больше 0
}