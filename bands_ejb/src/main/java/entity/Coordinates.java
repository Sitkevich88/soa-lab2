package entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@AllArgsConstructor
@Table(name = "coordinates")
public class Coordinates implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "x", nullable = false)
    private Long x; //Поле не может быть null

    @DecimalMax("967.0")
    @Column(name = "y", nullable = false)
    private Double y; //Максимальное значение поля: 967, Поле не может быть null

    public Coordinates() {

    }
}
