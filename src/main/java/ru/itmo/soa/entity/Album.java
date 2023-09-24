package ru.itmo.soa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "album")
@AllArgsConstructor
@NoArgsConstructor
@Data
@XmlRootElement
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonIgnore
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

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    private MusicBand musicBand;

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tracks=" + tracks +
                ", length=" + length +
                ", sales=" + sales +
                ", musicBand=" + musicBand.getName() +
                '}';
    }
}