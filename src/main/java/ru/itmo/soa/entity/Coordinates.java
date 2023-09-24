package ru.itmo.soa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "coordinates")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@XmlRootElement
public class Coordinates {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonIgnore
    private Integer id;

    @Column(name = "x", nullable = false)
    private Long x; //Поле не может быть null

    @DecimalMax("967.0")
    @Column(name = "y", nullable = false)
    private Double y; //Максимальное значение поля: 967, Поле не может быть null
}