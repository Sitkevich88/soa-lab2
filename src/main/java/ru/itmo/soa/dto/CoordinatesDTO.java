package ru.itmo.soa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoordinatesDTO {
    private Long x; //Поле не может быть null
    private Double y; //Максимальное значение поля: 967, Поле не может быть null
}