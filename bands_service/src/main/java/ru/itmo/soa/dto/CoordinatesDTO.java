package ru.itmo.soa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoordinatesDTO {
    @NotNull
    private Long x; //Поле не может быть null
    
    @NotNull
    @DecimalMax("967.0")
    private Double y; //Максимальное значение поля: 967, Поле не может быть null
}