package ru.itmo.soa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "music_band")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class MusicBand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически

    @NotBlank
    @Column(name = "name")
    private String name; //Поле не может быть null, Строка не может быть пустой

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    private Coordinates coordinates; //Поле не может быть null
    
    @Column(name = "creation_date", nullable = false, columnDefinition = "TIMESTAMP")
    private java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    
    @Positive
    @Column(name = "number_of_participants")
    private int numberOfParticipants; //Значение поля должно быть больше 0

    @Column(name = "description")
    private String description; //Поле может быть null
    
    @Column(name = "establishment_date", nullable = false, columnDefinition = "DATE")
    private java.time.LocalDate establishmentDate; //Поле не может быть null

    @Column(name = "genre", nullable = false)
    @Enumerated(EnumType.STRING)
    private MusicGenre genre; //Поле не может быть null

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(nullable = false)
    private Album bestAlbum; //Поле не может быть null
}