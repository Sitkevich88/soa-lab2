package entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Entity
@Table(name = "album")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Album implements Serializable {
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